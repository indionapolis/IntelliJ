/**
 * Project name: HomeWork
 * Created by pavel on 22.01.2018.
 * Pavel Nikulin BS1-8
 * Copyright ©
 * v0.0
 */
public class ITP {

    public static void main(String[] args) {
        Range r1 = new Range(1,0);
        Range r2 = new Range(100,10);
        Range r3 = new Range(1,10);
        Range r4 = new Range(4,8);
        Range r5 = new Range(2,5);
        Range r6 = new Range(7,9);


        System.out.println(r1.isEquleRange(r2));
        System.out.println(r1.isEpty());
        System.out.println();
        System.out.println(r4.isSubRangeOf(r3));
        System.out.println(r3.isSuperRangeOf(r4));
        System.out.println();
        System.out.println(r5.leftOverlaps(r4));
        System.out.println(r5.rightOverlaps(r4));
        System.out.println(r5.overlaps(r6));
        System.out.println();
        r5.add(r4).print();
        r5.subtract(r4).print();

    }



}

class Range {
    int start = 0;
    int end = 0;
    boolean empty = false;

    public Range(int start, int end){
        if (start>end){
            empty = true;
            return;
        }
        this.start = start;
        this.end = end;
    }

    public void print(){
        if (empty){
            System.out.println("ø");
            return;
        }
        for (int i = this.start; i <= this.end; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public boolean isEquleRange(Range range){
        return (this.start == range.start && this.end == range.end);
    }


    public boolean isEpty(){
        return empty;
    }

    public boolean isSubRangeOf(Range range){
        return (this.start >= range.start && this.end <= range.end);
    }

    public boolean isSuperRangeOf(Range range){
        return (range.start >= this.start && range.end <= this.end);
    }

    public boolean leftOverlaps(Range range){
        return this.start <= range.end && this.start >= range.start;
    }

    public boolean rightOverlaps(Range range){
        return this.end <= range.end && this.end >= range.start;
    }

    public boolean overlaps(Range range){
        return (this.start > range.end || this.end < range.start);
    }

    public Range add(Range range){
        return new Range(Math.min(this.start, range.start), Math.max(this.end, range.end));
    }

    public Range subtract(Range range){
        int x,y;
        if (overlaps(range))return null;
        if(range.start >= this.start && range.start <= this.end){
            x = this.start;
            y = range.start - 1;
        }else {
            x = range.end + 1;
            y = this.end;
        }
        return new  Range(x, y);
    }
}
