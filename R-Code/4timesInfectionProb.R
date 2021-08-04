library(data.table)
#install.packages("plotly")
library(plotly)
#library(ggplot2)
setwd("C:/Users/Nutzer/repast/masterThesis/output/extremum")
getwd()



##################################################################
# Batch Run  all Threats global extremum
##################################################################
#jpeg("IP_LocalSearch_image.jpeg", width = 720, height = 720, quality=100)
jpeg("IP_RandomSearch_image.jpeg", width = 720, height = 720, quality=100)

#data <- fread("IP_LocalSearch_pop07.csv") 
data <- fread("IP_RandomSearch_pop07.csv") 


#threatNew <- c(0,20,40,60,80,100)
threatNew <- c(25,50,75,100)
infection <- c(0.02, 0.05, 0.1, 0.2)
tickCounter <-c(500:20000)
color1 <- c( "red", "blue", "green", "black")


old.par <- par(mfrow=c(2, 2))



for (r in threatNew) {
  plot(NULL, main=paste("Random Search, PoP = 0.7, Threat = ", r),
  #plot(NULL, main=paste("Local Search, PoP = 0.7, Threat = ", r),
       xlab="Tick", ylab="Percentage of extreme norm", xlim= c(500,20000), ylim =c(0,1))
  i = 1
  dataTi <- data[threat==r] 
  
  for (ip in infection) {
    
    dataT <- dataTi[IP == ip]
    meanGlobal <- c()
    
    for (ti in tickCounter) {
      tmp <- dataT[tick==ti]
      
      b = mean(tmp$global)
      meanGlobal <- append(meanGlobal, b)
    }
    
    lines(tickCounter, meanGlobal, col =color1[i], lwd ="2")
    i = i+1
  }
  legend("topleft", # Add legend to plot
         legend = c("IP = 0.02", "IP = 0.05",  "IP = 0.1", "IP = 0.2"),
         col = color1,
         lty = 1,
         cex = 0.75)
  print("muh")
  
}




print("muh")
par(old.par)
dev.off()