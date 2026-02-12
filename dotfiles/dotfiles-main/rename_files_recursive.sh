#!/bin/bash

# Set the max filename length
MAX_LENGTH=80

# Function to rename files in a directory
rename_files() {
  local dir="$1"

  # Loop through all files in the directory
  for file in "$dir"/*; do
    if [ -f "$file" ]; then
      # Get the filename
      filename="$(basename "$file")"

      # Remove special characters and non-ASCII characters
      new_filename=$(echo "$filename" | tr -cd '[:alnum:]_.-' | tr ' ' '_')

      # Trim the filename to the max length
      new_filename="${new_filename:0:$MAX_LENGTH}"

      # Check if the new name already exists
      counter=1
      while [ -f "${dir}/${new_filename}" ]; do
        # If the new name already exists, append a number
        extension="${new_filename##*.}"
        if [ "$extension" != "$new_filename" ]; then
          # File has an extension
          filename_without_extension="${new_filename%.*}"
          new_filename_without_extension="${filename_without_extension:0:$((MAX_LENGTH - ${#extension} - 1 - ${#counter}))}"
          new_filename="${new_filename_without_extension}_${counter}.${extension}"
        else
          # File doesn't have an extension
          new_filename="${new_filename:0:$((MAX_LENGTH - ${#counter} - 1))}_${counter}"
        fi
        ((counter++))
      done

      # Rename the file
      if [ "$filename" != "$new_filename" ]; then
        mv "$file" "${dir}/${new_filename}"
        echo "Renamed $file to ${dir}/${new_filename}"
      fi
    elif [ -d "$file" ]; then
      # Get the directory name
      dirname="$(basename "$file")"

      # Remove special characters and non-ASCII characters
      new_dirname=$(echo "$dirname" | tr -cd '[:alnum:]_.-' | tr ' ' '_')

      # Trim the directory name to the max length
      new_dirname="${new_dirname:0:$MAX_LENGTH}"

      # Check if the new directory name is different
      if [ "$dirname" != "$new_dirname" ]; then
        new_dirpath="${file%/*}/${new_dirname}"
        mv "$file" "$new_dirpath"
        echo "Renamed directory $file to $new_dirpath"
        rename_files "$new_dirpath"
      else
        # Recursively call the function for subdirectories
        rename_files "$file"
      fi
    fi
  done
}

# Call the function for the current directory
rename_files "."
