library(shiny)
library(ggplot2)
library(dplyr)
library(bslib)
library(plotly)
library(DT)
library(broom)
library(ggfortify)
library(mplot)
library(tidyverse)
library(caret)

red = read.csv("winequality-red.csv", sep = ";")
white = read.csv("winequality-white.csv", sep = ";")

name_ls = c("fixed_acid", "volatile_acid", "citric_acid", "residual_sugar", "chlorides", "free_so2", 
            "total_so2", "density", "ph", "sulphates", "alcohol", "quality")

colnames(red) = name_ls
colnames(white) = name_ls

as.numeric(white$fixed_acid)
as.numeric(white$volatile_acid)
as.numeric(white$citric_acid)
as.numeric(white$residual_sugar)
as.numeric(white$chlorides)
as.numeric(white$free_so2)
as.numeric(white$total_so2)
as.numeric(white$density)
as.numeric(white$ph)
as.numeric(white$sulphates)
as.numeric(white$alcohol)
as.numeric(white$quality)

as.numeric(red$fixed_acid)
as.numeric(red$volatile_acid)
as.numeric(red$citric_acid)
as.numeric(red$residual_sugar)
as.numeric(red$chlorides)
as.numeric(red$free_so2)
as.numeric(red$total_so2)
as.numeric(red$density)
as.numeric(red$ph)
as.numeric(red$sulphates)
as.numeric(red$alcohol)
as.numeric(red$quality)

red$chlorides = log(red$chlorides)
red$sulphates = log(red$sulphates)
white$volatile_acid = log(white$volatile_acid)
white$free_so2 = log(white$free_so2)
# Define UI for application that draws a histogram
ui <- fluidPage(
  navbarPage(
    title = "Winemaking game",
    tags$h5("Winemaker, let's make some wines today and see how yummy your wine is 😋😋"),
    
    # Choose the type of wine to predict
    sidebarLayout(
      sidebarPanel = sidebarPanel (
        selectInput(
          inputId = "wine", 
          label = "Which type of wine do you wanna make today?", 
          choices = c("White wine", "Red wine"), 
          multiple = FALSE),
        
        # Select predictors for red wine
        conditionalPanel(
          condition = "input.wine == 'Red wine'",
          tags$h5("Winemaker, how much of these components do you wanna include in your wine?"),
          
          # Choose the volatile acidity predictor for red wine quality
          sliderInput(
            inputId = "volatile_red",
            label = "Volatile acidity",
            min = 0.1,
            max = 2,
            value = 1.05,
            step = 0.001
          ),
          
          # Choose the ph predictor for red wine quality
          sliderInput(
            inputId = "ph_red",
            label = "pH",
            min = 2.5,
            max = 4.5,
            value = 3.5,
            step = 0.01
          ),
          
          # Choose the alcohol predictor for red wine quality
          sliderInput(
            inputId = "alcohol_red",
            label = "Alcohol",
            min = 8,
            max = 15,
            value = 11.5,
            step = 0.1
          ),
          
          # Choose the chlorides predictor for red wine quality
          sliderInput(
            inputId = "chlorides",
            label = "Chlorides",
            min = 0.001,
            max = 0.75,
            value = 0.3745,
            step = 0.001,
          ),
          
          # Choose the sulphates predictor for red wine quality
          sliderInput(
            inputId = "sulphates_red",
            label = "Sulphates",
            min = 0.25,
            max = 2,
            value = 1.15,
            step = 0.01),
          # Choose the total sulfur dioxide predictor for red wine quality
          sliderInput(
            inputId = "total_so2_red",
            label = "Total sulfur dioxide",
            min = 5,
            max = 290, 
            value = 147.5,
            step = 1)
        ),
        
        # Select predictors for white wine
        conditionalPanel(
          condition = "input.wine == 'White wine'",
          tags$h5("Winemaker, how much of these components do you wanna include in your wine?"),
          
          # Choose the fixed acidity predictor for white wine quality
          sliderInput(
            inputId = "fixed_acid",
            label = "Fixed acidity",
            min = 3.5,
            max = 14.5,
            value = 9,
            step = 0.1,
          ),
          
          # Choose the residual sugar predictor for white wine quality
          sliderInput(
            inputId = "res_sugar",
            label = "Residual sugar",
            min = 0.5,
            max = 31.5,
            value = 16,
            step = 0.1
          ),
          
          # Choose the density predictor for white wine quality
          sliderInput(
            inputId = "density",
            label = "Density",
            min = 0.98,
            max = 1,
            value = 0.99,
            step = 0.0001
          ),
          
          # Choose the ph predictor for white wine quality
          sliderInput(
            inputId = "ph_white",
            label = "pH",
            min = 2.5,
            max = 4,
            value = 3.25, 
            step = 0.01
          ),
          
          # Choose the sulphates predictor for white wine quality
          sliderInput(
            inputId = "sulphates_white",
            label = "Sulphates",
            min = 0.1,
            max = 1.2,
            value = 0.65,
            step = 0.01
          ),
          
          # Choose the total sulfur dioxide predictor for red wine quality
          sliderInput(
            inputId = "total_so2_white",
            label = "Total sulfur dioxide",
            min = 8,
            max = 440, 
            value = 224,
            step = 1),
          
          # Choose the alcohol predictor for white wine quality
          sliderInput(
            inputId = "alcohol_white",
            label = "Alcohol",
            min = 8,
            max = 14.5,
            value = 11.25,
            step = 0.01
          ),
          
          # Choose the volatile acidity predictor for white wine quality
          sliderInput(
            inputId = "volatile_white",
            label = "Volatile acidity",
            min = 0.01,
            max = 1.2, 
            value = 0.6,
            step = 0.001
          ),
          
          # Choose the free sulfur dioxide predictor for white wine quality
          sliderInput(
            inputId = "free_so2",
            label = "Free sulfur dioxide",
            min = 1,
            max = 300,
            value = 150,
            step = 1
          )
        )
      ),
      
      mainPanel = mainPanel (
        tabsetPanel(
          
          # Output the result of the game
          tabPanel(
            title = "Let's play the game!",
            dataTableOutput("summaries"),
            uiOutput("result"),
            plotlyOutput("quality_hist"),
            uiOutput("ranking"),
            uiOutput("comment")
          ),
          
          # Output the theories behind the game
          tabPanel(
            title = "Don't believe your result?",
            uiOutput("subtitle"),
            uiOutput("model_type"),
            uiOutput("predictors"),
            verbatimTextOutput("model_code"),
            uiOutput("model_construction_explanation"),
            uiOutput("formula"),
            uiOutput("assumption_heading"),
            plotOutput("assumptions"),
            uiOutput("assumptions_explained"),
            uiOutput("final_words")
          )
        )
      )
    )
  )
)

# Define server logic required to draw a histogram
server <- function(input, output) {
  
  # Calculate the predicted quality based on user's inputs
  predicted_qual = reactive({
    if (input$wine == "White wine") {
      predicted_qual = 134 + 0.0665*input$fixed_acid + 0.0739*input$res_sugar - 0.00164 * input$total_so2_white - 
        136*input$density + 0.609*input$ph_white + 0.617*input$sulphates_white + 0.205*input$alcohol_white - 
        0.524*log(input$volatile_white + 0.305*log(input$free_so2))
    }
    else {
      predicted_qual = 4.53 - 0.958*input$volatile_red - 0.468*input$ph_red + 0.28*input$alcohol_red - 
        0.00233*input$total_so2_red - 0.275*log(input$chlorides) + 0.756*log(input$sulphates_red)
    }
    return (predicted_qual)
  })
  
  # Calculate the predicted quality's ranking compared to other wine of the same type
  ranking = reactive({
    worse_df = filter(df(), quality < predicted_qual())
    worse_percentage = dim(worse_df)[1]/dim(df())[1]
    return (worse_percentage)
  })
  
  # Determine the data frame used based on the user's choice of wine type
  df = reactive({
    if (input$wine == "White wine") {
      df = white
    }
    else {
      df = red
    }
    return (df)
  })
  
  # Output the table showing user's option
  output$summaries = DT::renderDataTable ({
    if (input$wine == "White wine") {
      factors = c("Fixed acidity", "Residual sugar", "Density", "pH",
                  "Sulphates", "Alcohol", "Volatile acidity", "Free sulfur dioxide")
      values = c(input$fixed_acid, input$res_sugar, input$density, input$ph_white,
                 input$sulphates_white, input$alcohol_white, input$volatile_white, input$free_so2)
    }
    else {
      factors = c("Volatile acidity", "pH", "Alcohol", "Chlorides", "Sulphates")
      values = c(input$volatile_red, input$ph_red, input$alcohol_red,
                 input$chlorides, input$sulphates_red)
    }
    tibble("Factors" = factors, "Chosen Values" = values)
  })
  
  # Output the predicted quality of the wine
  output$result = renderUI({
    HTML(paste("Your wine is expected to score ", "<b>", round(predicted_qual(),2), "</b>", "out of 10"))
  })
  
  # Output the histogram for overall quality of wine
  output$quality_hist = renderPlotly({
    if (input$wine == "White wine") {
      hist_plot = ggplot(data = white) +
        aes(quality) +
        geom_bar(fill = "lightblue") +
        labs(title = "Vinho Verde White wine quality",
             x = "Wine quality", 
             y = "Count")
      hist_obj = hist(white$quality)
      cv = train(
        quality ~ fixed_acid + residual_sugar + total_so2 + density + ph + sulphates + alcohol + volatile_acid + free_so2, white,
        method = "lm",
        trControl = trainControl(
          method = "cv", number = 100, verboseIter = FALSE
        ))
    }
    else {
      hist_plot = ggplot(data = red) +
        aes(quality) +
        geom_bar(fill = "lightpink") + 
        labs(title = "Vinho Verde Red wine quality",
             x = "Wine quality",
             y = "Count")
      hist_obj = hist(red$quality)
      cv = train(
        quality ~ volatile_acid + total_so2 + ph + alcohol + chlorides + sulphates, red,
        method = "lm",
        trControl = trainControl(
          method = "cv", number = 100, verboseIter = FALSE
        )
      )
    }
    rmse = cv$results$RMSE
    hist_plot = hist_plot + 
      theme_bw() + 
      geom_rect(xmin = predicted_qual() - rmse,
                xmax = predicted_qual() + rmse,
                ymin = 0,
                ymax = max(hist_obj$counts)+500,
                fill = "green", alpha = 0.25)
    ggplotly(hist_plot)
  })
  
  # Output the ranking of the predicted quality of the wine
  output$ranking = renderUI({
    if (input$wine == "White wine") {
      HTML(paste("<i> Psst, did you know that your wine tastes better than <b>", round(ranking(),2)*100, 
                 "%</b> of all the White Vinho Verde wine examined by Cortez in 2009?</i>"))
    }
    else {
      HTML(paste("<i> Psst, did you know that your wine tastes better than <b>", round(ranking(),2)*100, 
                 "%</b> of all the Red Vinho Verde wine examined by Cortez in 2009?</i>"))
    }
  })
  
  # Output the additional comment
  output$comment = renderUI({
    if (ranking() < 0.5) {
      HTML(paste("<br> <b> Final result: </b> <br> Uh oh, your wine is good, but not good enough to survive in this market. Because of this, your startup went to bankruptcy 😔😔 Better luck next time!"))
    }
    else {
      HTML(paste("<br> <b> Final result: </b> <br> Congratulation winemaker! You rocked this wine market 🥳🥳 It's just a matter of time before you officially rule this industry 🤩🤩"))
    }
  })
  
  # Output the subtitle of the game justification
  output$subtitle = renderUI({
    if (ranking() < 0.5) {
      HTML(paste("<i> Oops, you lost the game? <br> How could that happen?? 🫢🫢 <br> You wanna sue us because of the unreliable result??? 😱😱 <br> Woah woah calm down bro! Why don't we discuss the theory behind this game together then?️</i>"))
    }
    else {
      HTML(paste("<i> Hooray, you are such an incredible winemaker 🥳🥳 <br> But wait, you wanna make wine in real life this time? 😮😮 <br> And you are wondering if this result is reliable in real life?? 🤔🤔 <br> Then, hesitate not, let's discuss the theory behind this game together!!</i>"))
    }
  })
  
  # Output the type of model used in this game
  output$model_type = renderUI ({
    HTML(paste("<br> <b><u> Model type: </b></u> <br> The model built to predict the wine quality in this game is a <b> multiple linear regression </b> with predictors depending on which type of wines we are predicting. <br> Since you are making <b><i>", 
               input$wine, "</b> </i>this time, let's just discuss the model related to this type. <br> <i> (If you are interested in the other type of wine, reselect it in the panel in your left) </i>"))
  })
  
  # Output the predictors of the model
  output$predictors = renderUI({
    if (input$wine == "White wine") {
      HTML(paste("<br> <b><u> Predictors: </b></u> <br> 1. Fixed acidity <br> 2. Volatile acidity <br> 3. pH <br> 4. Residual sugar <br> 5. Density <br> 6. Sulphates <br> 7. Alcohol <br> 8. Free sulfur dioxide <br> <br> <b><u> Model construction </b></u>"))
    }
    else { 
      HTML(paste("<br> <b><u> Predictors: </b></u> <br> 1. Volatile acidity <br> 2. pH <br> 3. Sulphates <br> 4. Alcohol <br> 5. Chlorides <br> <br> <b><u> Model construction </b></u>"))}
  })
  
  # Output the code for the model construction
  output$model_code = renderPrint({
    if (input$wine == "White wine"){
      lm(data = white, 
         formula = quality ~ fixed_acid + residual_sugar + total_so2 + density + ph + sulphates + alcohol + 
           volatile_acid + free_so2) |> tidy()
    }
    else {
      lm(data = red,
         formula = quality ~ volatile_acid + ph + alcohol + total_so2 + chlorides + sulphates) |> tidy()
    }
  })
  
  # Output model construction explanation
  output$model_construction_explanation = renderUI({
    HTML(paste("<i> The table above tells us the weight of each predictors in our predicted value of the wine quality.</i> <br><br> According to this table, we could predict the wine quality by the following formula: "))
  })
  
  # Output the model's interpretation
  output$formula = renderUI({
    if (input$wine == "White wine") {
      HTML(paste0("<br> <p style='font-size: 15px; color: purple; background-color: lightyellow; padding: 10px;'>", "Quality = 0.0665 x Fixed acidity + 0.0739 x Residual sugar <br> - 0.00164 x Total sulfur dioxide - 136 x Density + 0.609 x pH + 0.617 x Sulphates <br> + 0.205 x Alcohol - 0.524 x log(Volatile acidity) <br>+ 0.305 x log(Free sulfur dioxide) + 134", "</p>"))
    }
    else {
      HTML(paste0("<br> <p style='font-size: 15px; color: purple; background-color: lightyellow; padding: 10px;'>", "Quality = - 0.958 x Volatile acidity - 0.468 x pH + 0.28 x Alcohol <br> - 0.00233 x Total sulfur dioxide - 0.275 x Chlorides + 0.756 x Sulphates", "</p>"))
      
    }
  })
  
  # Output the heading for the assumption
  output$assumption_heading = renderUI({
    HTML(paste("<b><u> Assumptions checking: </b></u> <br> However, in building such a linear regression model, we have made some assumptions. Therefore, we now have to check it <i>(otherwise you might accuse us of cheating 😣😣)</i>. <br> The following plots are used to check the assumptions"))
  })
  
  # Output the assumption checking plots
  output$assumptions = renderPlot({
    if (input$wine == "White wine") {
      model = lm(data = white, 
                 formula = quality ~ fixed_acid + residual_sugar + total_so2 + density + ph + sulphates + alcohol + 
                   volatile_acid + free_so2)
    }
    else {
      model = lm(data = red,
                 formula = quality ~ volatile_acid + ph + alcohol + total_so2 + chlorides + sulphates)
    }
    autoplot(model, which = 1:2)
  })
  
  # Output the assumption comments
  output$assumptions_explained = renderUI({
    HTML(paste("<br> The 2 assumptions checked by these illustrations are:<br> <b><i> 1. Normality:</b></i> according to the QQ Plot, most of the points lie on the QQ line, except for some points at the beggining and in the end of the graph. This indicates that the data's distribution is reasonably normal. Moreover, as we have quite a large sample size, we can rely on the Central Limit Theorem and conclude that the data is normally distributed. <br> <b><i> 2. Homoskedasticity: </b> </i> Looking at the residual plot, the figures appear to be fairly symmetric, which indicates a relatively constant variance, and hence satisfying the homoskedasticity assumption"))
  })
  
  # Output final words
  output$final_words = renderUI({
    HTML(paste("<br> So, believe it or not, our game is statistically trustworthy 👍👍 Do you wanna give it another try after knowing the theorem behind it? 🤓🤓"))
  })
}

# Run the application 
shinyApp(ui = ui, server = server)