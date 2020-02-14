import pandas as pd
import seaborn as sns
import matplotlib as plt
from sklearn.ensemble import RandomForestClassifier
from sklearn.svm import SVC
from sklearn import svm
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import confusion_matrix, classification_report
from sklearn.preprocessing import StandardScaler, LabelEncoder
from sklearn.model_selection import train_test_split

#get_ipython().run_line_magic('matplotlib', 'inline')
import csv
import os
from pandas.core.frame import DataFrame

d = pd.read_csv('resources/combined_csv.csv',sep=',')

avgUSG = d['USG'].mean()
avgPS = d['PS'].mean()
avgPER = d['PER'].mean()

inputFileName = "resources/combined_csv.csv"
outputFileName = "_edited.csv"

with open(inputFileName) as infile, open(outputFileName, "w") as outfile:
    r = csv.reader(infile)
    w = csv.writer(outfile)
    w.writerow(next(r))
    for row in r:
        if row[1] == "": # changes blank likes to 0 likes
            row[1] = "0"
        if '@' in row[6]: # changes Opp to 0 for away, 1 for home
            row[6] = "0"
        else:
            row[6] = "1"
        if row[8] == "": # changes blank PS to the average PS
            row[8] = avgPS
        if row[9] == "": # changes blank USG to the average USG
            row[9] = avgUSG
        if row[10] == "": # changes blank PER to the average PER
            row[10] = avgPER
        row[13] = row[13].replace('%','') # removes % sign from Opp DvP

        if row[2] == "": # only writes row if player is not injured
            #if isinstance(row[29],float) and row[29] > 3.6:
                w.writerow(row)
            

data = pd.read_csv('_edited.csv',sep=',')
print(data.head())
"""
#print(data.isnull().sum())

newdata = data.drop(['Player Name','Inj','Pos','Team','Actual FP','Actual Min'],axis = 1)

#bins = pd.IntervalIndex.from_tuples([(-1, 1), (1, 3.6), (3.6, 5.4), (5.4,20)])
bins = pd.IntervalIndex.from_tuples([(0, 5.4), (5.4, 20)])

group_names = ['bad', 'ok', 'good']
newdata['Actual Val'] = pd.cut(newdata['Actual Val'], bins = bins, labels = group_names)
label_quality = LabelEncoder()

newdata['Actual Val'] = label_quality.fit_transform(newdata['Actual Val'])

X = newdata.drop('Actual Val',axis = 1)
y = newdata['Actual Val']

X_train, X_test, y_train, y_test = train_test_split(X,y,test_size = 0.2, random_state = 42)

sc = StandardScaler()
X_train = sc.fit_transform(X_train)
X_test = sc.transform(X_test)

rfc = RandomForestClassifier(n_estimators = 200, class_weight={1:10,0:1})
rfc.fit(X_train, y_train)
pred_rfc = rfc.predict(X_test)

print(classification_report(y_test,pred_rfc))
print(confusion_matrix(y_test,pred_rfc))



"""