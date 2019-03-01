# ghashcode
Solutions for Google Hashcode

Directories:
1. data: contains the input data.
2. problem_statement: contains the problem statement.
3. hashcodeYYYY: contains the code for problem of online qualification round of Google Hashcode held in the year YYYY.

Approach:

2019:

1. Assign a weight to each photo, which is the sum of frequency of all unique tags in the photo.
2. Greedily pick the photo in decreasing order of number of tags. If number of tags are equals then pick the one with greater weight.
3. Assign these photos side by side, ensuring minInterest is non-zero and two consecutive photos with vertical alignment are collapsed into one slide.
