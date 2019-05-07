//Pin connected to ST_CP of 74HC595
byte latchPin = 14;
//Pin connected to SH_CP of 74HC595
byte clockPin = 16;
//Pin connected to DS of 74HC595
byte dataPin = 15;


void setup() {
  //set pins to output so you can control the shift register
  pinMode(dataPin, OUTPUT);
  pinMode(latchPin, OUTPUT);
  pinMode(clockPin, OUTPUT);
  
  digitalWrite(dataPin, 0);
  digitalWrite(latchPin, 0);
  digitalWrite(clockPin, 0);
  
  delay(1000);
}


void loop() {
  
  int bitArray[] = {1, 3, 7, 15, 31, 63, 127, 255}; 
  
  for (byte i = 0; i <8; i++) {
    //shift first 8 bits; ends up in the second SR
     shiftOut(dataPin, clockPin, MSBFIRST, 0);

    //shift second 8-bits; ends up in the first SR
     shiftOut(dataPin, clockPin, MSBFIRST, bitArray[i]);  

    //toggle the LATCH (write HIGH, then write LOW)
     digitalWrite(latchPin, HIGH);
     digitalWrite(latchPin, LOW);

     delay(1000);
  }

  for (byte i =0 ; i <8; i++) {
    //shift first 8 bits; ends up in the second SR
    //shift out bitArray[i] (mode: MSBFIRST)
     shiftOut(dataPin, clockPin, MSBFIRST, bitArray[i]);  

    //shift second 8-bits; ends up in the first SR
    //shift out 255 (mode: MSBFIRST)
     shiftOut(dataPin, clockPin, MSBFIRST, 255);

    //toggle the LATCH (write HIGH, then write LOW)
     digitalWrite(latchPin, HIGH);
     digitalWrite(latchPin, LOW);

     delay(1000);
  }

for (byte i =7 ; i <0; i--) {
    //shift first 8 bits; ends up in the second SR
    //shift out bitArray[i] (mode: MSBFIRST)
     shiftOut(dataPin, clockPin, MSBFIRST, bitArray[i]);  

    //shift second 8-bits; ends up in the first SR
    //shift out 255 (mode: MSBFIRST)
     shiftOut(dataPin, clockPin, MSBFIRST, 255);

    //toggle the LATCH (write HIGH, then write LOW)
     digitalWrite(latchPin, HIGH);
     digitalWrite(latchPin, LOW);

     delay(1000);
  }

  
for (byte i =7 ; i <0; i--) {
    //shift first 8 bits; ends up in the second SR
    //shift out 0 (mode: MSBFIRST)
     shiftOut(dataPin, clockPin, MSBFIRST, 0);  

    //shift second 8-bits; ends up in the first SR
    //shift out bitArray[i] (mode: MSBFIRST)
     shiftOut(dataPin, clockPin, MSBFIRST, bitArray[i]);

    //toggle the LATCH (write HIGH, then write LOW)
     digitalWrite(latchPin, HIGH);
     digitalWrite(latchPin, LOW);

     delay(1000);
  }

  
}
