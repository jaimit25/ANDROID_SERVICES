import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  TextEditingController _text = TextEditingController();
  var battery = 0;
  static const _channel = MethodChannel('com.example.androidServices/service');

  
  _startService() async {
    // final int? rtn = await channel.invokeMethod('getValue');
    if(_text.text==null || _text.text.isEmpty)
      return;

      battery = await _channel.invokeMethod('startService',{
      'text': _text.text,
    });
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Service'),
      ),
      body: ListView(
        children: [
          SizedBox(
            height: 300,
          ),
          Text('$battery'),
           Padding(
             padding: const EdgeInsets.all(20.0),
             child: TextFormField(
               controller: _text,
               style: TextStyle(
                 fontSize: 16,
                 color: Colors.black,
                 debugLabel: "text", 
               ),
             ),
           ),
          Padding(
            padding: const EdgeInsets.all(19.0),
            child: ElevatedButton(onPressed: _startService, child: Text("Start Service (5 Seconds Run)")),
          ),
          
      ],),
    );
  }
}
