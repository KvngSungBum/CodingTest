package BaekJoon3;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Emoticon_14226_Answer {

    static int S;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());

        //[화면의 이모티콘 수][클립보드의 이모티콘 수]
        visited = new boolean[2001][2001];

        solution();

        br.close();
    }

    private static void solution() {
        Queue<Step> queue = new LinkedList<>();
        queue.add(new Step(1, 0, 0));

        while (!queue.isEmpty()) {
            Step now = queue.poll();

            int emoticon_num = now.emoticon_num;
            int clipboard_num = now.clipboard_num;
            int time = now.time;

            if (emoticon_num == S) {
                System.out.println(time);
                return;
            }

            if (emoticon_num > 0 && emoticon_num < 2000) {
                if (!visited[emoticon_num][emoticon_num]) {//복사
                    visited[emoticon_num][emoticon_num] = true;

                    queue.offer(new Step(emoticon_num, emoticon_num, time + 1));
                }

                if (!visited[emoticon_num - 1][clipboard_num]) {//삭제
                    visited[emoticon_num-1][clipboard_num] = true;

                    queue.offer(new Step(emoticon_num - 1, clipboard_num, time + 1));
                }
            }

            if (clipboard_num > 0 && emoticon_num + clipboard_num < 2000) {//붙여넣기
                if (!visited[emoticon_num + clipboard_num][clipboard_num]) {
                    visited[emoticon_num + clipboard_num][clipboard_num] = true;

                    queue.offer(new Step(emoticon_num + clipboard_num, clipboard_num, time + 1));
                }
            }
        }
    }

    static class Step{
        int emoticon_num;
        int clipboard_num;
        int time;

        public Step(int emoticon_num, int clipboard_num, int time) {
            this.emoticon_num = emoticon_num;
            this.clipboard_num = clipboard_num;
            this.time = time;
        }
    }
}
