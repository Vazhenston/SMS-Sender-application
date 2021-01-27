import 'package:flutter/material.dart';
import 'package:sms_app/views/login.dart';
import 'package:sms_app/views/sms_details.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.blue,
          visualDensity: VisualDensity.adaptivePlatformDensity,
        ),
        initialRoute: '/login',
        routes: {
          '/login': (context) => LogIn(),
          '/SMSDetails': (context) => SMSDetails(),
        });
  }
}
