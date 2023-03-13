package process;

import java.util.Random;

import cards.*;
import cards.Number;

public class InitialMainPack {
    private Pack main;

    public InitialMainPack(Pack m) {
        main = m;
    }
    public void SetMainCard() {
        for (int i = 1; i < 10; i++) {
            if (i % 5 != 0) {
                SetMainNumberCard(i,4);
            }
        }
        for (int i = 10; i < 30; i++) {
            if (i % 5 != 0 && i % 11 != 0) {
                SetMainNumberCard(i,2);
            }
        }
        for (int i = 1; i < 10; i++) {
            SetMainNumberCard(i * 11,1);
        }
        for (int i = 0; i < 5; i++) {
            SetMainNumberCard(i - 5,2);
        }
        SetMainNumberCard(0,3);
        SetMainNumberCard(-10,2);
        SetMainSpecialCard();
    }
    public void SetMainNumberCard(int n, int count) {
        for (int i = 0; i < count; i++) {
            Number card = new Number(n, GetRandomSeed());
            main.AddCard(card);
        }
    }
    public void SetMainSpecialCard() {
        for (int i = 0; i < 4; i++) {
            Change cc = new Change(GetRandomSeed());
            Reverse cr = new Reverse(GetRandomSeed());
            main.AddCard(cc);
            main.AddCard(cr);
        }
        for (int i = 0; i < 8; i++) {
            if (i == 0 || i == 2 || i == 3) {
                for (int j = 0; j < 2; j++) {
                    String s = Integer.toString(i) + " times";
                    Times ct = new Times(GetRandomSeed(), s, i);
                    main.AddCard(ct);
                }
            }
            else if (i == 1 || i == 4) {
                for (int j = 0; j < 2; j++) {
                    String s = "plus " + Integer.toString(i);
                    Plus cp = new Plus(GetRandomSeed(), s, i);
                    main.AddCard(cp);
                }
            }
            else {
                for (int j = 0; j < 2; j++) {
                    String s = "remainder divided by " + Integer.toString(i);
                    Mod cm = new Mod(GetRandomSeed(), s, i);
                    main.AddCard(cm);
                }
            }
        }
    }
    public String GetRandomSeed() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 12;
        Random random = new Random();
    
        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
        
        return generatedString;
    }
}
