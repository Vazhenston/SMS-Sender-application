class SMSData {
  num id;
  num phoneNumber;
  String message;

  SMSData({this.id, this.phoneNumber, this.message});

  factory SMSData.fromJson(Map<String, dynamic> json) {
    return SMSData(
      id: json['id'],
      phoneNumber: json['phoneNumber'],
      message: json['messageText'],
    );
  }
}

class SMSDataList {
  // ignore: non_constant_identifier_names
  final List<SMSData> SMSDatas;

  // ignore: non_constant_identifier_names
  SMSDataList({this.SMSDatas});

  factory SMSDataList.fromJson(List<dynamic> parsedJson) {

    // ignore: non_constant_identifier_names
    List<SMSData> SMSDatas = new List<SMSData>();
    SMSDatas = parsedJson.map((i)=>SMSData.fromJson(i)).toList();

    return SMSDataList(
      SMSDatas: SMSDatas,
    );
  }
}

class LogInUser {
  num phoneNumber;

  LogInUser({this.phoneNumber});

}