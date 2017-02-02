(ns weekmenu2.containers.calendar
  (:require [cljs-time.core :as time]
            [cljs-time.coerce :as coerce]
            [cljs-time.format :as time-format]))


(def day-fm (time-format/formatter "EEE dd"))
(def month-fm (time-format/formatter "MMM"))
(def plain-date (time-format/formatter "dd-MM-YYYY"))


(defn week-seq [base start end]
  (let [d (.getDay base)]
    (for [x (range start end)]
      (time/plus base (time/days (- x d)))
      )))


(defn to-day-str [date-a]
  (->> date-a
      coerce/to-date-time
      (time-format/unparse plain-date))
  )

(defn day-view [date marked]
  "Renders a day view"
  ;; MARKED IS ALWAYS FALSE!!!
  [:div {:class (if marked "marked") }
   [:p (time-format/unparse month-fm date)]
   [:p
    [:strong (time-format/unparse day-fm date)]]])


(defn week-view 
  "Generate the view for a single week or a part of a week"
  ([base-day]
   (week-view base-day 0 7))

  ([base-day d-start d-end]
   [:ul.list-unstyled.list-inline.calendar-week
    (map
     #(identity ^{:key %} [:li (day-view % (= (to-day-str %) (to-day-str (time/today))))])
     (week-seq base-day d-start d-end))]) 
  )

(defn calendar-view []
  "Generate the view contaier for the calendar"
  [:ul.list-unstyled
   [:li (week-view (time/minus (time/now) (time/weeks 1)) 4 7)]
   [:li (week-view (time/now))]
   [:li (week-view (time/plus (time/now) (time/weeks 1)) 0 3)]]  
  )


(comment (if true "foo" "bar"))
