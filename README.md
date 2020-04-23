# Food Delivery App
Food delivery application
## Problem Statement
Having a busy overloaded modern lifestyle, everybody wants an automated system thathelpsin their  personal  activitiesandresults  in  economize  resources.  Rapid  evolution  in  the  usage  of smartphone and  web technology  shows  impact  on  the  migration  of  offline  services  to  online services.    In  Holocene  epoch,  Delivery  systems  are  playing  a  pivotal  role  in  providing  online services within a limited time and at greater level of convenience. Among all the delivery services, food delivery systems are now gaining more popularity in today’s market. These services are really helping out people to have whatever food, whenever they want,in restless work schedules. But all these  services  are  concentrating  in  delivering  food  from  restaurants.  Recent  studies  statethat whoever  consumes  home-cooked  food  on  regular  basis stays longer,  healthier  happiest  lifestyle compared to others
### Solution?
This search engine helps you find the name of the movie and also recommends movies that have similar plot and genre by using concepts of **Locality Sensitive Hashing**. 

## Functionalities
 - Reads the .csv file of all movies with plots.
 - Removes all the characters that are not letters or numbers, converts the sentence to lowercase and removes the stopwords occuring in the document.
 - Removes all the documents that doesnot have plots and applies the necessary function to the documents that have plots.
 - Tokenizes all the words in a document's plot and stores them in a list, then it stores all the lists of individual plots into a corpus.
 - Create shingles of 3 words each and convert in to 32 bit interger value.
 - Create a boolean matrix of documents and shingles.
 - Generate 100 random hash_funcs.
 - Create a signature matrix using the minhashing concept.
 - Using the concept of Lsh we get the similarity between documents using different distance funcs.

## Tech Stack
Technologies to be used.
 - NLTK(Natural Langauge Tool Kit Library)
 - Binascii from bisect_right
 - Numpy and Pandas libraries
 - Regex Library 
 - Random and sys


## Team Members
 - [Nikhil Munigela](2017AAPS0418H)
 - [Jathin Badam](2017A3PS0495H)
 - [Venkata Sai Karthik Jagini](2017AAPS0371H)
 - [Nikhil Kandukuri](2017A3PS0497H)
