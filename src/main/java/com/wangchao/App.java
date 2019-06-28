package com.wangchao;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!\n" );
        for (int i = 0; i < args.length; i++) {
            System.out.println( "arg"+ i +": " + args[i] + "\n" );
        }
    }
}
