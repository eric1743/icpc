import java.util.HashSet;
import java.util.Set;

public class WC {

    static Set<Rank> ranks = new HashSet<>();
    static int[] teams = new int[4];

    public static void main(String[] args) {
        gameRecur(0, 1);

        int[] classifications = new int[5];
        for (Rank r : ranks) classifications[r.tieClass]++;

        System.out.println("Unique scores:   " + ranks.size());
        System.out.println("No ties:         " + classifications[0] + " *  1 = " + classifications[0] + " possible rankings.");
        System.out.println("1 Single ties:   " + classifications[1] + " *  2 = " + classifications[1] * 2 + " possible rankings.");
        System.out.println("2 Single ties:    " + classifications[2] + " *  4 =  " + classifications[2] * 4 + " possible rankings.");
        System.out.println("1 triple tie:     " + classifications[3] + " *  6 = " + classifications[3] * 6 + " possible rankings.");
        System.out.println("1 quadruple tie:   " + classifications[4] + " * 24 =  " + classifications[4] * 24 + " possible rankings.");
        System.out.print("                            ");
        System.out.println(classifications[0] + classifications[1] * 2 + classifications[2] * 4 + classifications[3] * 6 + classifications[4] * 24 + " total rankings possible.");
    }

    static void gameRecur(int homeIdx, int awayIdx) {
        Game cur = new Game(homeIdx, awayIdx);
        for (int i = 0; i < 3; i++) {
            cur.applyPoints(false, i);

            if (awayIdx < 3) gameRecur(homeIdx, awayIdx + 1);
            else if (homeIdx < 2) gameRecur(homeIdx + 1, homeIdx + 2);
            else ranks.add(new Rank());

            cur.applyPoints(true, i);
        }
    }

    static class Game {
        int home;
        int away;

        Game(int home, int away) {
            this.home = home;
            this.away = away;
        }

        void applyPoints(boolean undo, int result) { //0 home wins, 1 away wins, 2 draw
            int undoer = undo ? -1 : 1;
            if (result == 0) {
                teams[home] += undoer * 3;
            } else if (result == 1) {
                teams[away] += undoer * 3;
            } else {
                teams[home] += undoer;
                teams[away] += undoer;
            }
        }
    }

    static class Rank {
        int[] points = new int[4];
        int tieClass = 0;

        Rank() {
            for (int i = 0; i < 4; i++) {
                points[i] = teams[i];
            }
            int[] tally = new int[10];
            for (int i : points) {
                tally[i]++;
                if (tally[i] == 2) tieClass++;
                if (tally[i] > 2) tieClass = tally[i];
            }
        }

        @Override
        public int hashCode() {
            String hash = "";
            for(int i : points) hash += i;
            return Integer.parseInt(hash);
        }

        @Override
        public boolean equals(Object o) {
            Rank other = (Rank) o;
            for (int i = 0; i < 4; i++) {
                if (points[i] != other.points[i]) return false;
            }
            return true;
        }
    }
}
