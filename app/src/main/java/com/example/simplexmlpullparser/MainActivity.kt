package com.example.simplexmlpullparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        var xml_data = assets.open("xmldemofile.xml")
        var factory = XmlPullParserFactory.newInstance()
        var parser = factory.newPullParser()

        parser.setInput(xml_data, null)

        var event = parser.eventType
        while (event !== XmlPullParser.END_DOCUMENT) {
            var tag_name = parser.name
            when(event) {
                XmlPullParser.END_TAG -> {
                    if(tag_name == "student" || tag_name == "teacher") {
                        var name = "\n"+parser.getAttributeValue(0)+" "+parser.getAttributeValue(1)
                        editText.append(name)
                    }
                }
            }
            event = parser.next()
        }
    }
}


//pass the data from xml into EditText in activity_main.xml file.
//Get the xmlData from Assets
//initialize factory variable.
// Now pass xml data into parser. The second paramter is the inputStream.
//create var event and use event to loop through the data.