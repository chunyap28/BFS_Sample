/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author chunyap
 */
public class BFS {

    static List<Integer> process(List<Integer>[] nodes, int start, int size){
        List<Integer> distance = new ArrayList<>();
        for(int i=0; i<size; i++){            
            distance.add(-1);
        }
        
        distance.set(start-1, 0);
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);   
        while( !queue.isEmpty() ){
            //System.out.println("Process Level: " + level);
            //System.out.println(queue);
            //System.out.println(distance.get(9));            
            int currenti = queue.poll();
            if( nodes[currenti] == null ){
                continue;
            }
            for(int i=0; i<nodes[currenti].size(); i++){//for each adjacent nodes
                int nexti = nodes[currenti].get(i);
                if( distance.get( nexti-1) == -1 ){
                    distance.set( nexti-1, distance.get(currenti-1) + 6);
                    
                    queue.offer(nexti);
                }
            }
        }
        
        distance.remove(start-1);
        return distance;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        for(int i=0; i<q; i++){//for each query
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            List<Integer>[] nodes = new ArrayList[m+1];
            for(int j=0; j<n; j++){//for each connection
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                
                if( nodes[u] == null ){
                    nodes[u] = new ArrayList<>();
                }
                nodes[u].add(v);
                if( nodes[v] == null ){
                    nodes[v] = new ArrayList<>();
                }
                nodes[v].add(u);
            }
            
            int s = scanner.nextInt();
            
            //process
            List<Integer> result = process(nodes, s, m);
            
            //print result
            System.out.println("Result: " + result.toString().replaceAll("[,\\[\\]]", ""));
        }
        
        scanner.close();
    }
    
}
