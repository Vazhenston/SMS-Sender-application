import 'dart:convert';

import 'package:sms_app/environment.dart';
import 'package:http/http.dart' as http;
import 'package:sms_app/models/sms.dart';

class MessageService {
  Future<SMSDataList> getSMSDataList() async {
    var response = await http.get(Environment().apiUrl);
    if (response.statusCode == 200) {
      final jsonResponse = json.decode(response.body);
      var smsDataList = SMSDataList.fromJson(jsonResponse);
      return smsDataList;
    } else {
      throw Exception('Failed');
    }
  }
}
