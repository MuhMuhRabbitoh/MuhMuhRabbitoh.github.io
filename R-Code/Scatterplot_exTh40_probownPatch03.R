library(ggplot2)
library(data.table)
library(dplyr)

setwd("C:/Users/Nutzer/repast/masterThesis/output")
getwd()

##################################################################
# for-loop for threat 0,20,40,60,80,100 
# for hole time frame, mean value of 30 runs
##################################################################
data2 <- fread("Batch10_Threat40_probOwnPatch03_GlobalSearch.csv")
dataY <- data2[tick>=500]



#pdf(file="Mean_norm_for_5differentThreats_20_to_40.pdf")
colornames <- c("black", "blue", "green", "orange", "red")
tickCounter<- c(500:20000)
runNr <- c(1:10)
kValue <- c("k02", "k04", "k06", "k08", "k1")
kdesc <- c("0 - <0.2", "0.2 - <0.4", "0.4 - <0.6", "0.6 - <0.8", "0.8 - 1")


dim(dataY)
muDataY <- mutate(dataY, )
summary(dataY)

ggplot(dataY, aes(x=tick, y=k06)) +
  geom_line(lwd=2, color="blue") +
  #aes(x=tick, y=k04) +
  #geom_line(lwd=2, color= "black")

  geom_ribbon(aes(ymin=k06, ymax=k06),
              alpha=0.2)


  geom_line(lwd=2, color= "black") +
for (r in runNr) {    
  
  
  dataR <- dataY[run==r]
  ggplot(NULL, main=paste("External Threat 40,",  "for run = ", r),
       xlab="Tick", ylab="Count", xlim= c(500,20000), ylim =c(100,1500))
  
  i = 1
  for (kv in kValue) {
    k <-c()
    
    for (ti in tickCounter) {
      
      data <- dataR[tick==ti]
      dataN <- data[[kv]]
      k <-append(k, dataN )
      
    }
    lines(tickCounter, k, col = colornames[[i]], lwd ="3")
    i = i+1
  } 
  legend("topleft",                                       # Add legend to plot
         #legend = c("threat = 0", "threat = 20", "threat = 40", "threat = 60","threat = 80", "threat = 100"),
         legend = kdesc,
         col = colornames,
         lty = 1,
         cex = 0.75)
  
  print(paste( "muh", r))
}

#dev.off()      