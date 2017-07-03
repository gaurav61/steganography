# Steganography
It is used to conceal messages or information within other non-secret text or data.This project deals with hiding text file inside images.

### Prerequisites
JDK should be installed.

## Running the tests
### Compile
```
javac Stegno.java
```

### Encryption
```
java Stegno -enc input_image.png input_text.txt output_image.png
```
### Decryption
```
java Stegno -dec output_image.png output_text.txt 
```
    
