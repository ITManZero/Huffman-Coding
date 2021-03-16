# Huffman-Coding-Library
Compression algorithm to reduce the size of character.

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [How it's work ?](how-it's-work-?)
  * [Huffman Tree](#huffman-tree)
  * [Huffman Encoding](#huffman-encoding)
  * [Huffman Decoding](#huffman-decoding)
 * [Usage](#usage)
    * [Gradle](#gradle)
    * [Maven](#maven)
    * [Jar](#jar)


## How it's work ?

The main purpose of this technique recoding character to less number of bits.

The algorithm will generate code for each character during tree building phase and replace each character with it's code at the end it will write the new message
to compressed file.

## Huffman Tree

Suppose we have string "A A A A A A A A" the generated code for 'A' is 0 and for space 1 both are stored on 8bits.

Size of string 15 * 8 = 120bits.

Steps to build huffman tree:

- Getting set Of characters and its how many time is repetition.

- Create node for each character which it's contain the character and it's repetition.

- Sorting nodes by character repetition we will use priority queue for implementation.

- Create parents by poping two of the nodes from queue first one (smallest) left child and the second one right child.

![1](https://user-images.githubusercontent.com/73588285/111169166-64aea100-85ab-11eb-91ba-fd39993d3ba5.PNG)

## Huffman Encoding

In this phase we will replace each character with it's code "010101010101010"

and parse byte for each 7 character,in this case we got 3 Bytes

write 3 Bytes  as well as it's tree to new file let's call it compressed.

the most efficent way to store tree in the file i found it on [StackOverFlow](https://stackoverflow.com/questions/759707/efficient-way-of-storing-huffman-tree)

## Huffman Decoding

Parsing huffman tree for decoding the 01 string

we will pass the tree and scanning the tree for 0 go left ie tree for 1 go right 

and we must check at every node if node contains character or not.

### Usage
 
 ##### Jar
 Download [link](https://mega.nz/file/9tgnxCoQ#uDh5wiKyABSnX11b_bmcGN57gQIdnAH3j5JxDCSDl00)
