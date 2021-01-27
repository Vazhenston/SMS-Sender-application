import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:sms_app/dao/phone_list_repository_mock.dart';
import 'package:sms_app/models/sms.dart';
import 'package:sms_app/services/login_service.dart';

class LogIn extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return LogInState();
  }
}

class LogInState extends State<LogIn> {
  final formKey = GlobalKey<FormState>();
  LogInUser user = LogInUser();

  get logInService {
    return LogInService(new PhoneListRepositoryMock());
  }

  @override
  Widget build(BuildContext context) {
    final phoneNumberField = TextFormField(
      onSaved: (String value) => {user.phoneNumber = int.parse(value)},
      decoration: const InputDecoration(
        hintText: 'Phone number',
      ),
      validator: (value) {
        return requiredValidator(value);
      },
    );
    final logInButton = RaisedButton(
      child: Container(
        width: double.infinity,
        child: Center(child: Text('Sign-in')),
      ),
      onPressed: () {
        if (formKey.currentState.validate()) {
          formKey.currentState.save();
          logIn(this.user);
        }
      },
      padding: EdgeInsets.symmetric(vertical: 17, horizontal: 16),
      color: Colors.redAccent,
      textColor: Colors.white,
    );
    final spacing = SizedBox(
      height: 16,
    );
    return Scaffold(
      appBar: AppBar(title: Center(child: Text('SMS Sender')),
        backgroundColor: Colors.redAccent,
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Form(
            key: formKey,
            child: Column(
              children: <Widget>[
                Text(
                  'Account login',
                  style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
                ),
                spacing,
                Text(
                    'Login in your account to send sms'),
                spacing,
                phoneNumberField,
                spacing,
                logInButton,
                spacing,
              ],
            ),
          ),
        ),
      ),
    );
  }

  requiredValidator(value) {
    if (value.isEmpty) {
      return 'Enter phone number';
    } else if (!(logInService.getPointsPhones().contains(int.parse(value)))) {
      return 'Invalid phone number';
    }
    return null;
  }

  logIn(LogInUser logInUser) async {
    if (logInService.getPointsPhones().contains(logInUser.phoneNumber)) {
      redirectToSmsDetails();
    }
  }

  redirectToSmsDetails() {
    Navigator.pushNamed(context, '/SMSDetails');
  }
}
