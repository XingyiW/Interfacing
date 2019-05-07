

int sensorValue = 0;         // the sensor value
int sensorMin = 1023;        // minimum sensor value
int sensorMax = 0;           // maximum sensor value

int piezoPin = 23; // Piezo on Pin 8
int sensorPin = 1; 
//int ldrValue = 0; // Value read from the LDR
int ledPin = 9;

void setup() {
  // turn on piezo to signal the start of the calibration period:
  pinMode(piezoPin, OUTPUT);
  //digitalWrite(piezoPin, HIGH);

  pinMode(ledPin, OUTPUT);
  //digitalWrite(ledPin, HIGH);

  // calibrate during the first five seconds
  while (millis() < 5000) {
    sensorValue = analogRead(sensorPin);

    // record the maximum sensor value
    if (sensorValue > sensorMax) {
      sensorMax = sensorValue;
    }

    // record the minimum sensor value
    if (sensorValue < sensorMin) {
      sensorMin = sensorValue;
    }
  }
}

void loop() {

  sensorValue = analogRead(1); // read the value from the LDR
  sensorValue = map(sensorValue, sensorMin, sensorMax, 0, 1023);
  sensorValue = constrain(sensorValue, 0, 1023);
  
  tone(piezoPin,sensorValue); // play a 1000Hz tone from the piezo
  analogWrite(ledPin, sensorValue);
  delay(25); // wait a bit
  noTone(piezoPin); // stop the tone
  

  Serial.print("sensorValue: ");
  Serial.println(sensorValue);

  delay(25); // wait the amount of milliseconds in ldrValue
  
}
