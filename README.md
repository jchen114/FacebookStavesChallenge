FacebookStavesChallenge
=======================

A coding challenge for facebook


Challenge:
  Given a stick where each segment is assigned a numerical weighted value, find the two largest substicks of equal length such that when put together on end to end, the center of mass is exactly in the center. 

Input: A string of integers such i.e: 1 3 2 5 6 1 4 7 8 3 2 2 4 6

Output: The starting index of the first substick, the starting index of the second substick, and the length of the substick. i.e 2 8 5 (not the answer for above, just an example output)

In order to do this, I recursively found all the sticks starting from the largest substick: N/2 where N is the length of the stick.

# # # # # # # # # # # # # #
-------------
              -------------
I check this and then I check the next smallest stick. There are 6 possibilities

# # # # # # # # # # # # # #
-----------
            -----------      (1)
              ------------   (2)
                -----------  (3)
# # # # # # # # # # # # # #
  -----------
              -----------    (4)
                -----------  (5)
# # # # # # # # # # # # # #
    -----------
                -----------  (6)
                
Then I just keep going recursively down until I find the sticks.
              
