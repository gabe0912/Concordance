# Concordance
=======================

Bridgewater coding challenge #1
> Given an arbitrary text document written in English, write a program that will generate a concordance, i.e. an alphabetical list of all word occurrences, labeled with word frequencies. Bonus: label each word with the sentence numbers in which each occurrence appeared.


How to get the code:
--------------------

    $ git clone https://github.com/gabe0912/Concordance.git
    $ cd Concordance

How to run the program:
-----------------------
    $ java -jar out/artifacts/concordance_jar/concordance.jar inputDocument.txt

How to see the source code:
---------------------------
https://github.com/gabe0912/Concordance/tree/master/src/main/java

Clarifications/Assumptions:
---------------------------

1. Words are connonicallized to avoid comparator discrepancies. Example: Given --> given; Bonus: --> bonus
1. Compound words should be counted as one. Examples: e.g., high-touch, daughter-in-law
1. Words do not include punctuation - excluding specific abbreviations. Examples: i.e. --> i.e.; English, --> English ; etc... --> etc
1. Sentences are delimited with punctuations only. Examples:  `.` `!` `?`

Limitations:
-----------

1. Only supports one document at a time
1. Some abbreviations count as a sencence delimiter: "i.e. Bridgewater Assoc. becomes two sensences"
1. Unexpected punctuations will not be stripped
