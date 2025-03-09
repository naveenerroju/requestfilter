# Initial thoughts
Without looking at the extenstions. I see that the main objective is to make an appliation that handles high traffic. So Spring MVC architeture doesnt work. I should use Spring WebFlux. Also, it is mentioned that data should deduplicated. I know that tomcap server pool and data normal databases will not be compatible. I should think of reactive programming. Also, We do not store requests or unique requests count. Can we use in memory database here?

# After analysis
I should use reactive programming. for data storage, I should use Cache memory since we dont need to store it permenantly. I send unique counts to logs and reset it anyway. My experince is in Spring boot/mvc and RDBMS, I should use my problem solving and learning skills. Also looking at the extensions, I should keep my componant open for modificatins so it can be future proof. There shouldnt be many changes when changing from standard loging to data streaming.

# During implementation
As I'm good in Spring framwork, Webflux shouldnt be a challange. The biggest challange is running redis locally as I have never used redis personally. I tried connecting redis to spring application but there is a connection issue in runtime. My redis container is unable to connect to spring boot. But I do not much time to debug this issue. I will continue with my idea of implementation, So the interviewer will have an idea of my overall project building skills. 
The extension about endpoint is not clear to me. I will get it clarified and explain how i would do that during interview. For now, I will continue keeping time in mind.

# During extensions implementation
Implementing Kafka is not a challange, as I have used it earlier also. I set up the producer configurations and that should be enough as This application will not consume the logs, it only produces. I should also build the docker file, as zip file is not very handy.