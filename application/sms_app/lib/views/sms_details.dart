import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:sms_maintained/sms.dart';
import 'package:sms_app/models/sms.dart';
import 'package:sms_app/services/message_service.dart';
import 'dart:convert';
import 'dart:async';

class SMSDetails extends StatefulWidget {
  @override
  _SMSDetailsState createState() => _SMSDetailsState();
}

class _SMSDetailsState extends State<SMSDetails> {
  SmsSender sender = new SmsSender();
  Future<SMSDataList> resultDataMessages;
  var messageService = MessageService();
  final controller = ScrollController();
  double offset = 0;

  @override
  void initState() {
    resultDataMessages = messageService.getSMSDataList();
    controller.addListener(onScroll);
    super.initState();
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  void onScroll() {
    setState(() {
      offset = (controller.hasClients) ? controller.offset : 0;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Center(child: Text('SMS Sender')),
        backgroundColor: Colors.redAccent,
      ),
      body: FutureBuilder<SMSDataList>(
        future: resultDataMessages,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            List<SMSData> smsDataList = snapshot.data.SMSDatas;
            return SingleChildScrollView(
              scrollDirection: Axis.vertical,
              child: Padding(
                padding: const EdgeInsets.all(10.0),
                child: Column(
                  children: <Widget>[
                    Table(
                      defaultVerticalAlignment:
                          TableCellVerticalAlignment.middle,
                      border: TableBorder(
                        horizontalInside:
                            BorderSide(width: 2.0, color: Colors.redAccent),
                        verticalInside:
                            BorderSide(width: 2.0, color: Colors.redAccent),
                      ),
                      columnWidths: {
                        0: FlexColumnWidth(1),
                        1: FlexColumnWidth(3),
                      },
                      children: [
                        TableRow(
                          children: [
                            Center(
                              child: Text(
                                'Номер телефона',
                                style: TextStyle(
                                    fontSize: 15, fontWeight: FontWeight.bold),
                              ),
                            ),
                            Center(
                                child: Text(
                              'Сообщение',
                              style: TextStyle(
                                  fontSize: 15, fontWeight: FontWeight.bold),
                            )),
                          ],
                        ),
                        for (var item in smsDataList)
                          TableRow(
                            children: [
                              Padding(
                                padding: EdgeInsets.only(right: 2.0),
                                child: Center(
                                  child: Text(
                                    item.phoneNumber.toString(),
                                    style: TextStyle(
                                        fontSize: 13,
                                        fontWeight: FontWeight.bold),
                                  ),
                                ),
                              ),
                              Padding(
                                padding: EdgeInsets.only(left: 4.0, top: 3.0),
                                child: Center(
                                  child: Text(
                                    utf8convert(item.message),
                                    style: TextStyle(
                                        fontSize: 13,
                                        fontWeight: FontWeight.bold),
                                  ),
                                ),
                              ),
                            ],
                          ),
                      ],
                    ),
                    Row(
                      children: <Widget>[
                        Expanded(
                          child: Center(
                              child: RaisedButton(
                            child: Container(
                              child: Text('Разослать сообщения'),
                            ),
                            onPressed: () {
                              for (var item in smsDataList) {
                                sender.sendSms(new SmsMessage(
                                    item.phoneNumber.toString(),
                                    utf8convert(item.message)));
                              }
                            },
                            color: Colors.redAccent,
                            textColor: Colors.white,
                          )),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
            );
          } else if (snapshot.hasError) {
            print(snapshot.error);
            return AlertDialog(
              title: Text(
                'An Error Occured!',
                textAlign: TextAlign.center,
                style: TextStyle(
                  color: Colors.redAccent,
                ),
              ),
              content: Text(
                "${snapshot.error}",
                style: TextStyle(
                  color: Colors.blueAccent,
                ),
              ),
              actions: <Widget>[
                FlatButton(
                  child: Text(
                    'Go Back',
                    style: TextStyle(
                      color: Colors.redAccent,
                    ),
                  ),
                  onPressed: () {
                    Navigator.of(context).pop();
                  },
                )
              ],
            );
          }
          return Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                CircularProgressIndicator(),
                SizedBox(height: 20),
                Text('This may take some time..')
              ],
            ),
          );
        },
      ),
    );
  }

  static String utf8convert(String text) {
    List<int> bytes = text.toString().codeUnits;
    return utf8.decode(bytes);
  }
}
