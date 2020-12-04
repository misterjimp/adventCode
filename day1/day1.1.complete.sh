inputset=$(cat day1.puzzleData)
findResult=()

find_two_for_sum() {

  local sum=$1
  declare -A numbers
  local n=0
  local partner=""
  local number=""

  for number in $inputset; do
    partner=$(($sum-$number))
    eureka=${numbers[$partner]}
    n=$(($n+1))
    if [[ "$eureka" != "" ]]; then
      result=$(($number*$partner))
      findResult+=($number $partner)
      #numbers found return zero
      return 0
    else
      numbers[$number]=$partner
    fi
  done

  #We didnt find any numbers, return non zero code.
  return 1
}

find_three_for_sum() {
  sum=$1
  # for each number, call find two for the remaining sum.
  for number in $inputset; do
    remaining=$(($sum-$number))
    find_two_for_sum $remaining
    if [[ "$?" == "0" ]]; then
      #numbers found, findResult contains the two numbers, we just need to add the current nummber.
      findResult+=($number $partner)
      return 0
    fi
  done;
  #no numbers found, return non zero
  return 1
}

#Execution starts here

find_two_for_sum 2020

if [[ "${#findResult[@]}" == "2" ]];then
  echo "found numbers: ${findResult[0]}, ${findResult[1]}"
  echo "Result: $((${findResult[0]} * ${findResult[1]}))"
else
   echo "No numbers found"
fi

#Clear find array
findResult=()

find_three_for_sum 2020

if [[ "${#findResult[@]}" == "3" ]];then
  echo "found numbers: ${findResult[0]}, ${findResult[1]}, ${findResult[2]}"
  echo "Result: $((${findResult[0]} * ${findResult[1]} * ${findResult[2]}))"
else
   echo "No numbers found"
fi