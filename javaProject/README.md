known issues:
1. when user tries to add a known value, we will avoid adding a null into the list, and also send them back to the menu, we need to repropmt them to give another value instead of sending back to menu
2. is existsChecks iterate over all elements including nulls, maybe we can run over the size of the known elements instead the size of the initialized array
3. when user tries to view details of an empty list we dont print anything