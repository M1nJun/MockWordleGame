import random
wordbank = []
with open("wordle.txt") as f:
    for word in f.readlines():
        stripword = word.strip()
        wordbank.append(stripword)

rand = random.randint(0, len(wordbank))


word = wordbank[rand]
wrd = list(word)
stars = [ "*", "*", "*", "*", "*"]
colors = ["Grey", "Grey", "Grey", "Grey", "Grey"]
isGameDone = False

count = 0

print(word)

while count<5:
    guess = input("Guess a word: ")
    if len(guess) != 5:
        print("invalid word")
    if guess not in wordbank:
        print("invalid word")
    if guess == word:
        print("That is correct!")
    gss = list(guess)
    for i in range(len(wrd)):
        for j in range(len(gss)):
            if wrd[i] == gss[j]:
                if i == j:
                    colors[j] == "Green"
                    stars[j] == gss[j]
                else:
                    colors[j] == "Yellow"
    print(stars)
    print(colors)
    count = count + 1