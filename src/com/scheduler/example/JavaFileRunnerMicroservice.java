package com.scheduler.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ProcessBuilder;
import java.lang.ProcessBuilder.Redirect;
import java.net.URL;
import java.net.URLClassLoader;

import com.example.project.*;

public class JavaFileRunnerMicroservice {
	static ClassLoader classLoader = JavaFileRunnerMicroservice.class.getClassLoader();

	public static void printClassPath() {

        if (classLoader instanceof URLClassLoader) {
            URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
            URL[] urls = urlClassLoader.getURLs();

            System.out.println("Classpath:");

            for (URL url : urls) {
                System.out.println(url.getFile());
            }
        } else {
            System.out.println("Classpath retrieval not supported for this class loader.");
        }
	}

	static int counter = 1;

	public static void main(String[] args) {
		String currentDirectory = System.getProperty("user.dir");
		// System.out.println(currentDirectory);
		
		
		
		
		//printClassPath();
		
		

		File log = new File("java-version.log");
		// TODO Auto-generated method stub
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		executorService.scheduleAtFixedRate(() -> {
			
			
			
			// Execute your Java file here
            // You can use Java's ProcessBuilder to run the Java file as a separate process
            // Example:
			
			//This is a Java class that allows you to create and configure an external process, such as running a command or executing another program.
            ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "../helloWorldJarFile.jar","com.example.project.HelloWorld",counter+"");
            //ProcessBuilder processBuilder2 = new ProcessBuilder("java", "-cp", "lib/helloWorldJarFile.jar","com.example.project.AnotherClassExample");

            counter += 1;
            processBuilder.redirectErrorStream(true);
            
            

           try {

//    			System.out.println("Executing process builder content");
    			processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
    			//processBuilder2.redirectOutput(ProcessBuilder.Redirect.INHERIT);

    			processBuilder.start();
    			//processBuilder2.start();
    			processBuilder.redirectOutput(log);
//    			System.out.println("Content Execution Successful!!");
    			
			} catch (IOException e) {
				// TODO Auto-generated catch block

				System.out.println("Exception on processBuilder try/catch block ---> " + e);
			}
			
//			System.out.println("Execute after 3 seconds delay");
			
		}, 0, 1, TimeUnit.SECONDS);
		//executorService.shutdown();
	}
}
