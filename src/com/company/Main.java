package com.company;
import java.util.*;
import java.io.File;

public class Main {
    public static final Random gen = new Random();
    public static final  Scanner scan = new Scanner(System.in);

    public static int[] randomNumberCollection(int numberOfNumbers, int maxNumber)
    {
        if(numberOfNumbers > maxNumber)
        {
            return null;
        }

        int[] result = new int[numberOfNumbers];
        Set<Integer> numbersUsed = new HashSet<>();
        for(int i=0; i < numberOfNumbers; i++)
        {
            int newNumberToAdd;
            do
                {
                    newNumberToAdd = gen.nextInt(maxNumber+1);
                } while (numbersUsed.contains(newNumberToAdd));
            result[i] = newNumberToAdd;
            numbersUsed.add(newNumberToAdd);
        }

        return result;
    }

    public static int askQuestion(String[] splitQuestion)
    {
        int value = 0;
        ArrayList<String> userQuestion = new ArrayList<>();

        for(int i=0; i <6 ; i++)
        {
            userQuestion.add(splitQuestion[i]);
        }
        System.out.println(userQuestion.get(0));
        System.out.println("1."+ (userQuestion.get(1)));
        System.out.println("2."+ (userQuestion.get(2)));
        System.out.println("3."+ (userQuestion.get(3)));
        System.out.println("4."+ (userQuestion.get(4)));
        String answer = scan.next();
        if(answer.equalsIgnoreCase(userQuestion.get(5)))
        {
            System.out.println("You got it right");
            return value+1;
        }
        else
        {
            System.out.println("You got it Wrong");
            return value;
        }


    }

    public static void main(String[] args) {
        HashMap<Integer,String> question = new HashMap<Integer, String>();
        try
        {
            int count = 0;
            File quiz = new File("quizzer.txt");
            Scanner reader = new Scanner(quiz);
            while (reader.hasNextLine())
            {
                question.put(count,reader.nextLine());
                count++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Please Enter Your name.");
        String userName = scan.nextLine();
        int[] questionsToAsk = randomNumberCollection(5,question.size()-1);
        int score = 0;
        for(int i=0; i<questionsToAsk.length;i++) {
            String[] splitQuestion = question.get(questionsToAsk[i]).split(",");
            if (askQuestion(splitQuestion) == 1) {
                System.out.println("You got a point");
                score++;
            }
        }
        System.out.println(userName+ " got a score of "+score);
    }
}
