library(data.table)
#install.packages("plotly")
library(plotly)
#library(ggplot2)
setwd("C:/Users/Nutzer/repast/masterThesis/output/extremum")
getwd()


jpeg("POP07_BothSearch_image.jpeg", width = 960, height = 480, quality=100)
old.par <- par(mfrow=c(1, 2))

##################################################################
# Batch Run Local Search all Threats global extremum
##################################################################


data <- fread("LocalSearch_Pop07_20406080100.csv") 


threatNew <- c(0,20,40,60,80,100)

tickCounter <-c(500:20000)
color1 <- c( "red", "orange","green", "blue", "purple", "black")

i = 7

plot(NULL, main=paste("Local Search, pop = 0.7"),
     xlab="Tick", ylab="Percentage of extreme norm", xlim= c(500,20000), ylim =c(0,1))

for (r in threatNew) {
  
  i = i-1
  dataT <- data[threat==r] 

  meanGlobal <- c()
  
  for (ti in tickCounter) {
    tmp <- dataT[tick==ti]
    
    b = mean(tmp$global)
    
    meanGlobal <- append(meanGlobal, b)
  }
  
  lines(tickCounter, meanGlobal, col =color1[i], lwd ="2")
  
}
legend("topleft", # Add legend to plot
       legend = c("Threat = 100", "Threat = 80", "Threat = 60", "Threat = 40", "Threat = 20", "Threat = 0"),
       col = color1,
       lty = 1,
       cex = 0.75)
print("muh")



##################################################################
# Batch Run random Search all Threats global extremum
##################################################################


data <- fread("RandomSearch_Pop07_20406080100.csv") 


threatNew <- c(0,20,40,60,80,100)


tickCounter <-c(500:20000)
color1 <- c( "red", "orange","green", "blue", "purple", "black")
i = 7


plot(NULL, main=paste("Random Search, pop = 0.7"),
     xlab="Tick", ylab="Percentage of extreme norm", xlim= c(500,20000), ylim =c(0,1))

for (r in threatNew) {
  
  i = i-1
  dataT <- data[threat==r] 
  
  
  
  meanGlobal <- c()
  
  for (ti in tickCounter) {
    tmp <- dataT[tick==ti]
    
    b = mean(tmp$global)
    meanGlobal <- append(meanGlobal, b)
  }
  
  lines(tickCounter, meanGlobal, col =color1[i], lwd ="2")
  
}
legend("topleft", # Add legend to plot
       legend = c("Threat = 100", "Threat = 80", "Threat = 60", "Threat = 40", "Threat = 20", "Threat = 0"),
       col = color1,
       lty = 1,
       cex = 0.75)
print("muh")
par(old.par)
dev.off()
