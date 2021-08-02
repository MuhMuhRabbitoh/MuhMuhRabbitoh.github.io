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
data500 <- data2[tick>=500]



#pdf(file="Mean_norm_for_5differentThreats_20_to_40.pdf")
colornames <- c("black", "blue", "green", "orange", "red")
tickCounter<- c(500:20000)
runNr <- c(1:10)
kValue <- c("k02", "k04", "k06", "k08", "k1")
kdesc <- c("0 - <0.2", "0.2 - <0.4", "0.4 - <0.6", "0.6 - <0.8", "0.8 - 1")

i = 1

for (k in kValue) {
  
    vekSD <- c()
    vekMean <- c()
    vekYmin <- c()
    vekYmax <- c()
    
    
    for (ti in tickCounter) {
      
      
      data <- data500[tick==ti]
      dataN <- data[[k]]
      
      Mean = mean(dataN)
      Ymin = min(dataN)
      Ymax = max(dataN)
      SD = sd(dataN)
      
      
      vekMean <-append(vekMean, Mean )
      vekYmin <-append(vekYmin, Ymin )
      vekYmax <-append(vekYmax, Ymax )
      vekSD <-append(vekSD, SD )
         
    }
    plot(NULL, main=paste("External Threat 40,",  "for norm = ", kdesc[i]),
         xlab="Tick", ylab="Count", xlim= c(500,20000), ylim =c(0,1500))
      lines(tickCounter, vekMean, col = "black", lwd ="3")
      lines(tickCounter, vekYmin, col = colornames[i], lwd ="2")
      lines(tickCounter, vekYmax, col = colornames[i], lwd ="2")
      lines(tickCounter, vekSD, col = "purple", lwd ="2")
      
      legend("topleft", # Add legend to plot
             legend = kdesc,
             col = colornames,
             lty = 1,
             cex = 0.75)
  
    print("muh")
    i = i +1
}

