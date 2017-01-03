(ns weekmenu2.containers.calendar
  (:require [cljs-time.core :as time]
            [cljs-time.format :as time-format]))


(def day-fm (time-format/formatter "EEE dd"))

(def month-fm (time-format/formatter "MMM"))


(defn week-seq [base start end]
  (let [d (.getDay base)]
    (for [x (range start end)]
      (time/plus base (time/days (- x d)))
      )))

(defn day-view [date]
  "Renders a day view"
  [:div 
   [:p (time-format/unparse month-fm date)]
   [:p
    [:strong (time-format/unparse day-fm date)]]])


(defn week-view 
  ([base-day]
   (week-view base-day 0 7))

  ([base-day d-start d-end]
   [:ul
    (map
     #(identity ^{:key %} [:li (day-view %)])
     (week-seq base-day d-start d-end))]) 
  )

(defn calendar-view []
  [:ul
   [:li (week-view (time/minus (time/now) (time/weeks 1)) 4 7)]
   [:li (week-view (time/now))]
   [:li (week-view (time/plus (time/now) (time/weeks 1)) 0 3)]]
  
  )
