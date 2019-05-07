
const int dataPin = 17;  
const int latchPin = 15; 
const int clockPin = 16; 
const int thermPin = 0;


int code; 
float celsius;
int digit;

const char common = 'a';    // common anode

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
  pinMode(thermPin, INPUT);

  digitalWrite(dataPin, 0);
  digitalWrite(clockPin, 0);
  digitalWrite(latchPin, 0);

  if( common == 'a' )
  for(int i=0; i<(signed)sizeof(symbols); i++){
      symbols[i] ^= B11111111; //flip all bits using XOR
  }
}


void loop() {

  code = analogRead(thermPin);
  if (code <= 289) {
    celsius = 5 + (code - 289) / 9.82;
  }
  if (code > 289 && code <= 342) {
    celsius = 10 + (code - 342) / 10.60;
  }
  if (code > 342 && code <= 398) {
    celsius = 15 + (code - 398) / 11.12;
  }
  if (code > 398 && code <= 455) {
    celsius = 20 + (code - 455) / 11.36;
  }
  if (code > 455 && code <= 512) {
    celsius = 25 + (code - 512) / 11.32;
  }
  if (code > 512 && code <= 566) {
    celsius = 30 + (code - 566) / 11.00;
  }
  if (code > 566 && code <= 619) {
    celsius = 35 + (code - 619) / 10.44;
  }
  if (code > 619 && code <= 667) {
    celsius = 40 + (code - 667) / 9.73;
  }
  if (code > 667) {
    celsius = 45 + (code - 712) / 8.90;
  }


  Serial.print("Temperature: ");
  Serial.print(celsius);
  Serial.println("Celsius");
  
  digit = (int)(celsius *= 10); // convert decimal to int (3-digit)
  digit %=10; // last digit for final result
  Serial.println(digit);
  
  digitalWrite(latchPin, LOW);
  shiftOut(dataPin, clockPin, LSBFIRST, symbols[digit]);
  digitalWrite(latchPin, HIGH);
  delay(1000);
  
}
