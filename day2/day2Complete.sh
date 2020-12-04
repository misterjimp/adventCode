inputset=$(cat day2.puzzleData)

#Global return variable
globalRet=""

#general regex, returns the first submatch f the regex
#in the globalRet varaible.
#return status confirms if the input had a regex match or not.

get_regex() {
  regex=$1
  input=$2

  [[ $input =~ $regex ]]
  ret=$?
  #echo "ret: $ret"
  #echo "input: $input"
  #echo "regex: $regex"

  globalRet="${BASH_REMATCH[1]}"
  #echo "${BASH_REMATCH[@]}"
  return $ret
}

#selectors

get_low_bound() {
  local input=$1
  local regex='([0-9]+)-'

  get_regex $regex "$input"
  return $?
}

get_letter() {
  local input=$1
  local regex='([a-zA-Z]):'

  get_regex $regex "$input"
  return $?
}

get_high_bound() {
  local input=$1
  local regex='-([0-9]+)'

  get_regex $regex "$input"
  return $?
}

get_password() {                                                                                                                                                                                            local input=$1                                                                                                                                                                                            local regex='[0-9]+-[0-9]+\s[a-zA-Z]:\s([a-zA-Z]+)'
  get_regex $regex "$input"
  return $?
}


#operators
count_letters() {
  local letter=$1
  local password=$2
  n=0

  for (( i=0; i<${#password}; i++ )); do
    if [[ "${password:$i:1}" == "$letter" ]]; then
      n=$(($n + 1))
    fi
  done

  return $n
}

is_password_valid_task1() {
  input=$1 #Assume an entire line of policy and password
  get_password "$input"
  password=$globalRet
  get_low_bound "$input"
  lowBound=$globalRet
  get_high_bound "$input"
  highBound=$globalRet
  get_letter "$input"
  letter=$globalRet

  count_letters "$letter" "$password"
  nrOfLetters=$?

  if [ "$nrOfLetters" -lt "$lowBound" ]; then
    #to few letters
    return 1
  elif [ "$nrOfLetters" -gt "$highBound" ]; then
    #to many letters
    return 1
  else
    # correct password
    return 0
  fi
}

is_password_valid_task2() {
  input=$1 #Assume an entire line of policy and password
  get_password "$input"
  password=$globalRet
  get_low_bound "$input"
  firstPos=$(($globalRet - 1))
  get_high_bound "$input"
  secPos=$(($globalRet - 1))
  get_letter "$input"
  letter=$globalRet

  if [[ "${password:$firstPos:1}" == "$letter" ]] && [[ "${password:$secPos:1}" != "$letter" ]]; then
    #ok
    return 0
  elif [[ "${password:$firstPos:1}" != "$letter" ]] && [[ "${password:$secPos:1}" == "$letter" ]]; then
    #ok
    return 0
  else
    # incorrect password
    return 1
  fi
}

#Execution starts here

okPasswords=0
total=0

while IFS= read -r line; do
  total=$(($total + 1))
  is_password_valid_task1 "$line"
  if [[ "$?" == "0" ]]; then
    okPasswords=$(($okPasswords + 1))
  fi
done <<< $inputset

echo "Task1 ok passwords: $okPasswords of $total"
okPasswords=0
total=0

while IFS= read -r line; do
  total=$(($total + 1))
  is_password_valid_task2 "$line"
  if [[ "$?" == "0" ]]; then
    okPasswords=$(($okPasswords + 1))
  fi
done <<< $inputset

echo "Task2 ok passwords: $okPasswords of $total"

# tests
wrong1="1-4 h: hurhhaa"
wrong2="5-6 p: purokfds"
right1="1-2 h: hello"
right2="2-6 e: nomade"

echo "Tests..."
is_password_valid_task2 "$wrong1"
echo "wrong1 : $?"

is_password_valid_task2 "$wrong2"
echo "wrong2 : $?"

is_password_valid_task2 "$right1"
echo "right1 : $?"

is_password_valid_task2 "$right2"
echo "right2 : $?"