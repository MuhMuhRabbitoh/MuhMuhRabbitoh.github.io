library(data.table)
#install.packages("plotly")
library(plotly)
#library(ggplot2)
setwd("C:/Users/Nutzer/repast/masterThesis/output")
getwd()


##################################################################
# for-loop for threat 0,10,20,30,40,50,60,70,80,90,100 
# for hole time frame, mean value of 30 runs
##################################################################
data2 <- fread("ModelOutputBatch30.csv")
dataY <- data2[tick>=500]

threatNew <- c(0,10,20,30,40,50,60,70,80,90,100)
titlenum <- c("< 0.2", "0.2 - 0.4","0.4 - 0.6", "0.6 - 0.8","> 0.8")


pdf(file="Mean_norm_for_all_ticks.pdf")

testCount = 505

for (step in threatNew) {


          dataThreat100 <- dataY[ExThreat==step]
          
          k02 <-c()
          k04 <-c()
          k06 <-c()
          k08 <-c()
          k1 <-c()
          #threat<- c()
          sd02 <-c()
          sd04 <-c()
          sd06 <-c()
          sd08 <-c()
          sd1 <-c()
          
          
          tickCounter<- c(500:testCount)
          co <- c("k02","k04","k06","k08","k1")
          for (v in co) {
            
            vek <- c()
            vek2 <- c()
            
            for (ticker in tickCounter) {
              data <- dataThreat100[tick==ticker]
              dataMean <- mean(data[[v]])
              sdmean <- sd(data[[v]])
              dataMean <- round(dataMean, digits = 0)
              sdMean <- round(dataMean, digits = 0)
              vek <- append(vek, dataMean)
              vek2 <- append(vek, sdMean)
              if(ticker ==testCount && v=="k02"){
                k02 <-append(k02, vek )
                sd02 <-append(sd02, vek2 )
              }
              if(ticker ==testCount && v=="k04"){
                k04 <-append(k04, vek )
                sd04 <-append(sd04, vek2 )
              }
              if(ticker ==testCount && v=="k06"){
                k06 <-append(k06, vek )
                sd06 <-append(sd06, vek2 )
              }
              if(ticker ==testCount && v=="k08"){
                k08 <-append(k08, vek )
                sd08 <-append(sd08, vek2 )
              }
              if(ticker ==testCount && v=="k1"){
                k1 <-append(k1, vek )
                sd1 <-append(sd1, vek2 )
              }
            }
          }  
          tickvek <- c(500:testCount)
          allvek <- c(tickvek,k02,sd02,k04,sd04,k06,sd06,k08,sd08,k1,sd1)
          ma <- matrix(allvek, ncol =11)
          colname <- c("tick","k02","sd02","k04","sd04","k06","sd06","k08","sd08","k1","sd1")  
          colnames(ma) <- colname
          #print(ma)
          write.csv(ma,'ma.csv')
          #d <- fread("ma2.csv")
          #print(d)
          
          d <- fread("ma.csv")
          #print(d)
          
          plot(NULL, main=paste("External Threat = 100"),
               xlab="Tick", ylab="Count", xlim= c(500,20000), ylim =c(0,1300))
          
          lines(d$tick, d$k02, col ="black", lwd ="2")
          lines(d$tick, d$k04, col ="blue", lwd ="2")
          lines(d$tick, d$k06, col ="green", lwd ="2")
          lines(d$tick, d$k08, col ="orange", lwd ="2")
          lines(d$tick, d$k1, col ="red", lwd ="2")
}

print("muh")
dev.off()  
#colname <- c("Tick","ExThreat","k02","k04","k06","k08","k1")  
