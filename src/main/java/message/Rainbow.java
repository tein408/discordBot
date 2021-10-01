package message;

import java.util.ArrayDeque;
import java.util.Deque;

public class Rainbow {

    String[] arr = new String[9];
    int divide, mod;
    StringBuilder sb = new StringBuilder();

    public Rainbow() {
        arr[0] = "<:arr0:889816882864095292>";
        arr[1] = "<:arr1:889816882914402345>";
        arr[2] = "<:arr2:889816882729852949>";
        arr[3] = "<:arr3:889816882822135839>";
        arr[4] = "<:arr4:889816885120626699>";
        arr[5] = "<:arr5:889816884697001985>";
        arr[6] = "<:arr6:889816884696981505>";
        arr[7] = "<:arr7:889816884759900191>";
        arr[8] = "<:arr8:889816885166759978>";
    }

    public String printRainbow(int cnt) {
        divide = cnt/9;
        mod = cnt%9;

        if(cnt > 72) {
            divide = 8;
            mod = 0;
        }

        if(divide > 0) {
            String sum = "";
            for(int i=0; i<9; i++) {
                sum += arr[i];
            }
            for(int i=0; i<divide; i++) {
                sb.append(sum + "\n");
            }
        }
        if(mod > 0) {
            String temp = "";
            for(int i=0; i<mod; i++) {
                temp += arr[i];
            }
            sb.append(temp);
        }
        return sb.toString();
    }

    public String rainbowArt(int cnt) {
        divide = cnt/9;
        mod = cnt%9;

        if(cnt > 72) {
            divide = 8;
            mod = 0;
        }

        Deque<String> deque = new ArrayDeque<>();
        for(int i=0; i<9; i++) {
            deque.addLast(arr[i]);
        }

        String temp = "";

        if(divide > 0) {
            for(int j=0; j<divide; j++) {
                for(int i=0; i<deque.size(); i++) {
                    temp = deque.pollFirst();
                    sb.append(temp);
                    deque.addLast(temp);
                }
                temp = deque.pollFirst();
                deque.addLast(temp);
                sb.append("\n");
            }
        }
        if(mod > 0) {
            String modTemp = "";
            for(int i=0; i<mod; i++) {
                temp = deque.pollFirst();
                modTemp += temp;
                deque.addLast(temp);
            }
            sb.append(modTemp);
        }

        return sb.toString();
    }

}
