# double-decryptable-ciphers
Program that looks for English words that decrypt/encrypt (caesar cipher) to other English words

GOAL
The goal of this program was to find english words that encrypt/decrypt to other english words. Results.txt holds all english words that encrypt & decrypt to another english word based on word_list.txt. usable.txt is some words I've collected that are the most usable for messages. The load function on line 36 of Main.java goes through all words in word_list.txt and decrypts/encrypts them with shifts 1-25, brute-force checking if any words decrypt/encrypt with any shift to another word, if so write that word in results.txt.

Basically finding words that make sentences like:
'_bin bug, hi won_'

Which looks like a normal sentence with normal words, but it holds a secret message.
When decryted with a shift of 20 it yields:
'_hot ham, no cut_'
The original sentence didn't look like it was a secret/encrypted message but it was. A sort of disguised encryption. 

Also x2 decryptable messages like:
'_VCH VOA, BC QIH_'

Which decrypts to reveal:
'_bin bug, hi won_'
Which of course doesn't make any sense. In reality you must decrypt it once more to reach the actual secret message! Which once again is:
'_hot ham, no cut_'

That's all really, I think it's pretty neat.
