library(data.table)
#install.packages("plotly")
library(plotly)
#library(ggplot2)
setwd("C:/Users/Nutzer/repast/masterThesis/output/extremum")
getwd()


##################################################################
# Single Run global
##################################################################

jpeg("Local_Global_Extremum.jpeg", width = 720, height = 720, quality=100)
old.par <- par(mfrow=c(2, 2))

data <- fread("LocalSearch_Pop07_20406080100.csv")

threatNew <- c(20, 80)
tickCounter <-c(500:20000)



for (r in threatNew) {
  dataT <- data[threat==r] 
  plot(NULL, main=paste("Extremum for local Observation, PoP = 0.7, Threat =", r),
       xlab="Tick", ylab="Percentage of extreme norm behavior", xlim= c(500,20000), ylim =c(0,1))
  
  meanLocal <-c()
  meanGlobal <- c()
  
  for (ti in tickCounter) {
    tmp <- dataT[tick==ti]
    a = mean(tmp$local)
    b = mean(tmp$global)
    meanLocal <- append(meanLocal, a)
    meanGlobal <- append(meanGlobal, b)
  }
  
  
  lines(tickCounter, meanLocal, col ="blue", lwd ="2")
  lines(tickCounter, meanGlobal, col ="red", lwd ="2")
  legend("topleft", # Add legend to plot
         legend = c("global Extremum", "local Extremum"),
         col = c("red", "blue"),
         lty = 1,
         cex = 0.75)
}
print("muh1")

##################################################################
# Batch Run random Search
##################################################################

data <- fread("randomSearch_pop03_threat45.csv")
#data <- fread("Extremum_globalSearch_batch10_20bis60.csv") 


threatNew <- c(45)

tickCounter <-c(500:20000)


for (r in threatNew) {
  dataT <- data[threat==r] 
  plot(NULL, main=paste("Extremum for global Observation, PoP = 0.3, Threat =", r),
       xlab="Tick", ylab="Percentage of extreme norm behavior", xlim= c(500,20000), ylim =c(0,1))
  
  meanLocal <-c()
  meanGlobal <- c()
  
  for (ti in tickCounter) {
    tmp <- dataT[tick==ti]
    a = mean(tmp$local)
    b = mean(tmp$global)
    meanLocal <- append(meanLocal, a)
    meanGlobal <- append(meanGlobal, b)
  }
  
  
  lines(tickCounter, meanLocal, col ="blue", lwd ="2")
  lines(tickCounter, meanGlobal, col ="red", lwd ="2")
  legend("topleft", # Add legend to plot
         legend = c("global Extremum", "local Extremum"),
         col = c("red", "blue"),
         lty = 1,
         cex = 0.75)
}
print("muh2")


##################################################################
# batch Run 10 local Search
##################################################################


data <- fread("localSearch_pop07_threat45.csv")

threatNew <- c(45)
tickCounter <-c(500:20000)


for (r in threatNew) {
  dataT <- data[threat==r] 
  plot(NULL, main=paste("Extremum for local Observation, PoP = 0.7, Threat =", r),
       xlab="Tick", ylab="Percentage of extreme norm behavior", xlim= c(500,20000), ylim =c(0,1))
  
  meanLocal <-c()
  meanGlobal <- c()
  
  for (ti in tickCounter) {
    tmp <- dataT[tick==ti]
    a = mean(tmp$local)
    b = mean(tmp$global)
    meanLocal <- append(meanLocal, a)
    meanGlobal <- append(meanGlobal, b)
  }
  
  
  lines(tickCounter, meanLocal, col ="blue", lwd ="2")
  lines(tickCounter, meanGlobal, col ="red", lwd ="2")
  legend("topleft", # Add legend to plot
         legend = c("global Extremum", "local Extremum"),
         col = c("red", "blue"),
         lty = 1,
         cex = 0.75)
}


print("muh muh")
par(old.par)
dev.off()
