
#include "IntervalTimer.h"

//volatile uint32_t timerCounter=0;
//IntervalTimer myTimer;

const int dataPin = 17;  // 74HC595 pin 14
const int latchPin = 15; // pin 12
const int clockPin = 16; // pin 11
const int pot =4; //analog read in 4, pin 18
volatile int potValue = 0;

/* uncomment one of the following lines that describes your display
 *  and comment out the line that does not describe your display */
const char common = 'a';    // common anode
 
unsigned int cnt = 0;
byte symbol, symbols[] = {
B11111100, // 0
B01100000, // 1
B11011010, // 2
B11110010, // 3
B01100110, // 4
B10110110, // 5
B10111110, // 6
B11100000, // 7
B11111110, // 8
B11110110, // 9
B11101110, // A
B00111110, // B
B10011100, // C
B01111010, // D
B10011110, // E
B10001110 // F
};

void setup() {
  
  // initialize I/O pins
  pinMode(dataPin, OUTPUT);
  pinMode(latchPin, OUTPUT);
  pinMode(clockPin, OUTPUT);
  pinMode(pot, INPUT);

  if( common == 'a' )
  for(int i=0; i<(signed)sizeof(symbols); i++){
      symbols[i] ^= B11111111; //flip all bits using XOR
  }
}


void loop() {
  
  potValue = analogRead(pot);
  Serial.print("potValue: ");
  Serial.println(potValue);

  potValue%=10;
  Serial.print("lastdigit: ");
  Serial.println(potValue);
  
  digitalWrite(latchPin, LOW);
  shiftOut(dataPin, clockPin, LSBFIRST, symbols[potValue]);
  digitalWrite(latchPin, HIGH);
  delay(800);
  
}
