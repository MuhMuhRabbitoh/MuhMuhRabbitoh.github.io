library(data.table)
#install.packages("plotly")
library(plotly)
#library(ggplot2)
setwd("C:/Users/Nutzer/repast/masterThesis/output")
getwd()


##################################################################
# for-loop for threat 0,20,40,60,80,100 
# for hole time frame, mean value of 30 runs
##################################################################
data2 <- fread("ModelOutputBatch30.csv")
dataY <- data2[tick>=500]

titlenum <- c("< 0.2", "0.2 - 0.4","0.4 - 0.6", "0.6 - 0.8","> 0.8")

#pdf(file="Count_Mean_norm_for_all_norms.pdf")
colornames <- c("black", "red", "green", "blue", "cyan")
tickCounter<- c(500:20000)
#threat <- c(0,20,40,60,80,100)
#threat <- c(20,25,30,35,40)
threat <- c(45)
kValue <- c("k02", "k04", "k06", "k08", "k1")
kdesc <- c("0 - <0.2", "0.2 - <0.4", "0.4 - <0.6", "0.6 - <0.8", "0.8 - 1")
i = 1


for (th in threat) {    
  
  dataThreat <- dataY[ExThreat==th]
  plot(NULL, main=paste("External Threat = ",th),
       xlab="Tick", ylab="Count", xlim= c(500,20000), ylim =c(0,1200))
  
  i = i+1
  colorstepper =1
  for (kv in kValue) {
    k <-c()
    
    for (ti in tickCounter) {
      
      data <- dataThreat[tick==ti]
      dataN <- data[[kv]]
      dataMean <- mean(dataN)
      
      k <-append(k, dataMean )
      
    }
    lines(tickCounter, k, col =colorstepper, lwd ="3")
    
    colorstepper = colorstepper +1  
  } 
  legend("topleft",                                       # Add legend to plot
         legend = c("norm = 0 - <0.2", "norm = 0.2 - <0.4", "norm = 0.4 - <0.6", "norm = 0.6 - <0.8","norm = 0.8 - 1"),
         col = colornames,
         lty = 1,
         cex = 0.75)
  
  print( "muh")
}

#dev.off()      

