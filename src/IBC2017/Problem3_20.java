package sav;

import jdk.internal.dynalink.support.BottomGuardingDynamicLinker;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pavel on 07.08.17.
 */
public class Problem3_20 {
    public static void main(String[] args) {
        GameSet new_game = new GameSet();
        new_game.StartGame(); // Start Game
    }


    /**
     *  class GameSet
     *  Создаем массив с ходами.
     *  метод StartGame - начинае игру
     *  метод Play - непосредственно игра
     *  Игра не имеет ограничения на ход игрока, но по условию 10 или 1
     *  метод GetIntFromString берет числовое значение из строки (начальная точка, ход)
     *  компьютер ходит основываясь на масиве Alice[], если он в клетке true, значит у него есть выигрышная тактика,
     *  иначе он делает по одному ходу (+1), ожидая позиции true, и тогда см. начало предидущей строки.
     *  Не стал жестко подводить игру к условиям, чтобы осталась возможность для быстрой кастомизации
     *  Надеюсь смысл программы можно будет отследить по ключевым словам.
     */

    static class GameSet {
        private boolean     Alice[];
        private boolean     out = false;
        private int     Location = 0;
        private int     Start_Point;

        Scanner in = new Scanner(System.in);

        public GameSet() {
            this.Alice = new boolean[110];
            Location = 0;
            for (int i = 109; i > 98; i--) {
                this.Alice[i] = false;
            }
            this.Alice[98] = true;
            for (int i = 97; i != 0; i--) {
                if ((!this.Alice[i + 1]) && (!this.Alice[i + 10])) {
                    this.Alice[i] = true;
                } else {
                    this.Alice[i] = false;
                }
            }
        }

        private void StartGame(){
            System.out.print("Enter start point: ");
            this.Start_Point = GetIntFromString(in.nextLine());
            if (this.Start_Point > -1 && this.Start_Point < 100){
                Play(this.Start_Point);
            }
            else {
                System.out.println("Invalid start point, sorry");
            }
        }

        public void Play(int point){
            Change_Location(point);
            while (true){
                GetMove();
                if (this.out){
                    System.out.println("Goodbye");
                    break;
                }
                if (this.Location > 99){
                    System.out.println("Game Over");
                    System.out.println("You lose!");
                    break;
                }
                MakeMove();
                if (this.Location > 99){
                    System.out.println("Game Over");
                    System.out.println("You Win!");
                    break;}
            }

        }

        private void GetMove(){
            System.out.print("Enter your move: ");
            String step = in.nextLine();
            if(step.equals("quit")){
                this.out = true;
            }
            else {
                Change_Location(GetIntFromString(step));
                System.out.println("Position: " + this.Location);
            }
        }

        private void MakeMove(){
            if (Alice[this.Location]) {
                if (this.Location < 89) {
                    this.Change_Location(10);
                    System.out.println("Comp move: +" + 10);
                } else {
                    this.Change_Location(1);
                    System.out.println("Comp move: +" + 1);
                }
            }
            else {
                this.Change_Location(1);
                System.out.println("Comp move: +" + 1);
            }
            System.out.println("Position: " + Location);
        }

        public void Change_Location(int x){
            this.Location += x;
        }


        public static int GetIntFromString(String x) {
            String digit = new String();
            Pattern pat = Pattern.compile("[-]?[0-9]");
            Matcher matcher = pat.matcher(x);
            while (matcher.find()) {
                digit += matcher.group();
            }
            return Integer.parseInt(digit);
        }
    }
}