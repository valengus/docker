#!/bin/bash 

echo "Cleaning up previous workflows"
# rm -f .github/workflows/*

containers=( $(ls | grep :) )

for container in "${containers[@]}"
do

  if [ -f "${container}/Dockerfile" ] 
    then
      echo "Dockerfile in ${container} exists."
      echo "Creating workflow yml file for ${container}"
      touch .github/workflows/${container}.yml
    else
      echo "Dockerfile in ${container} doesnt exists."
  fi
done