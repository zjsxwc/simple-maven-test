
`mvn compile`生成 target 目录后，可以命令行执行
`/bin/sh -c cd /home/wangchao/IdeaProjects/quicktest && java -DsystemProperty1=value1 -DsystemProperty2=value2 -XX:MaxPermSize=256m -classpath /home/wangchao/IdeaProjects/quicktest/target/classes com.wangchao.App arg1 arg2`

也可以执行
`mvn exec:exec`




https://blog.csdn.net/johnnywww/article/details/7964326

mvn clean package

打包可执行jar

java -jar target/quicktest-1.0-SNAPSHOT.jar  xzc xccczxczxc



```
  <properties>
    <!-- the main class -->
    <exec.mainClass>io.vertx.example.HelloWorldEmbedded</exec.mainClass>
  </properties>

 <build>

    <pluginManagement>
      <plugins>
        <!-- We specify the Maven compiler plugin as we need to set it to Java 1.8 -->
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.1</version>
          <configuration>
            <source>1.8</source>
            <target>1.8</target>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>

    <!--
    You only need the part below if you want to build your application into a fat executable jar.
    This is a jar that contains all the dependencies required to run it, so you can just run it with
    java -jar
    -->
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>2.3</version>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
            <configuration>
              <transformers>
                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                  <manifestEntries>
                    <Main-Class>${exec.mainClass}</Main-Class>
                  </manifestEntries>
                </transformer>
                <transformer implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
                  <resource>META-INF/services/io.vertx.core.spi.VerticleFactory</resource>
                </transformer>
              </transformers>
              <artifactSet>
              </artifactSet>
              <outputFile>${project.build.directory}/${project.artifactId}-${project.version}-fat.jar</outputFile>
            </configuration>
          </execution>
        </executions>
      </plugin>

      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.4.0</version>
        <executions>
          <execution>
            <!-- run the application using the fat jar -->
            <id>run-app</id>
            <goals>
              <goal>exec</goal>
            </goals>
            <configuration>
              <executable>java</executable>
              <arguments>
                <argument>-jar</argument>
                <argument>target/${project.artifactId}-${project.version}-fat.jar</argument>
              </arguments>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
</build>



To run with maven

mvn compile exec:java

To build a "fat jar"

mvn package

To run the fat jar:

java -jar target/maven-simplest-3.7.1-fat.jar

```