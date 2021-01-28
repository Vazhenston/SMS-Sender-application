# SMS Sender
### Application for reading data from Google Sheets and sending SMS using this data
## Description
I worked in the pick-up point and I had to check google sheets and to send SMS manually to clients, who did not pick up their packages. I wanted to automate it so 
as not to waste time on it.
<br>
The main condition is not to use paid services for sending SMS, to use just SMS traffic in work phone.
<br>
I wanted to use Google Messages for web, but Google do not have one's API. Therefore I wrote the android app in Flutter.
<br>
Since this is my first project, the main tasks were writing a CRUD MVC-application in Java and using basic tools (Spring Boot, Hibernate, Maven, DB).
## Using
**0. We have a template table in Goolge Sheets**
<br>

![Image alt](https://github.com/Vazhenston/pictures/raw/main/table.png)
<br>
**1. Log in via Google**
<br>

![Image alt](https://github.com/Vazhenston/pictures/raw/main/oauth.png)
<br>
**2. Enter data**
- The pick-up point address for composing a message
- If today date is order date plus 3 days, we have to send SMS
<br>

![Image alt](https://github.com/Vazhenston/pictures/raw/main/enter.png)
<br>
**3. Then we can check messages and phone numbers, edit, delete ones or to add more (typical CRUD app)**
<br>

![Image alt](https://github.com/Vazhenston/pictures/raw/main/web.png)
<br>
**4. Start app on an Android smartphone and login there. Phone number must equals work phone number**
<br>

![Image alt](https://github.com/Vazhenston/pictures/raw/main/login.jpg)
<br>
**5. Send SMS**
<br>

![Image alt](https://github.com/Vazhenston/pictures/raw/main/app.jpg)
<br>
## Future scope
- [ ] To complete implementing authorization. If we have one pick-up point, the application will work. Else all data of all tables will be merged
- [ ] To remove data after sending SMS. Now we need to remove one manually
- [ ] To add refresh button in android app. Now we have new data, we need login anew to refresh data
- [ ] To build the good front-end
- [ ] To make possible to choose SIM-card. Now app sends SMS from default SIM-card
## P.S.
It is my first full application. I know that it has many disadvantages, perhaps later I will fix it
<br>
All the best
