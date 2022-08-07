#!/bin/bash 

echo "Cleaning up previous workflows"
rm -f .github/workflows/*

containers=( $(ls | grep :) )

for container in "${containers[@]}"
do
  echo "${container}"
  if [ -d "${container}" ] 
    then
      echo "Directory ${container} exists."
      echo "Creating workflow yml filefor ${container}"
      touch .github/workflows/${container}
  fi
done