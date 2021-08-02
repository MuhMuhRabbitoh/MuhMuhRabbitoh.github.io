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

threatNew <- c(0,10,20,30,40,50,60,70,80,90,100)
titlenum <- c("< 0.2", "0.2 - 0.4","0.4 - 0.6", "0.6 - 0.8","> 0.8")


pdf(file="Mean_norm_for_5differentThreats_20_to_40.pdf")
colornames <- c("black", "red", "green", "blue", "cyan", "purple")
tickCounter<- c(500:20000)
threat <- c(20,25,30,35,40)
kValue <- c("k02", "k04", "k06", "k08", "k1")
kdesc <- c("0 - <0.2", "0.2 - <0.4", "0.4 - <0.6", "0.6 - <0.8", "0.8 - 1")
i = 1


for (kv in kValue) {    
    
  plot(NULL, main=paste("External Threat between 20 and 40,",  "for k = ", kdesc[i]),
       xlab="Tick", ylab="Count", xlim= c(500,20000), ylim =c(200,1000))
    
    i = i+1
    colorstepper =1
    for (th in threat) {
        k <-c()
        dataThreat100 <- dataY[ExThreat==th]
        for (ti in tickCounter) {
          
          data <- dataThreat100[tick==ti]
          dataN <- data[[kv]]
          dataMean <- mean(dataN)
          
          k <-append(k, dataMean )
          
        }
        lines(tickCounter, k, col =colorstepper, lwd ="3")
     
        colorstepper = colorstepper +1  
    } 
    legend("topleft",                                       # Add legend to plot
           #legend = c("threat = 0", "threat = 20", "threat = 40", "threat = 60","threat = 80", "threat = 100"),
           legend = c("threat = 20", "threat = 25", "threat = 30","threat = 35", "threat = 40"),
           col = colornames,
           lty = 1,
           cex = 0.75)
    
    print( "muh")
}
    
dev.off()      

