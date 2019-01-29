# Undo recent commits
git reset --soft HEAD^   # Most recent commit
git reset --soft HEAD~x  # Go back 'x' commits
git reset --hard HEAD^   # Undo most recent commit AND remove changes

# Undo a 'git add'
git reset <file> # Specific file
git reset        # All files

# Remove all commits in repository
# -- WARNING -- Will delete all history PERMANENTLY
git checkout --orphan latest_branch
git add -A 
git commit -am "commit message"
git branch -D master 
git branch -m master 
git push -f origin master 

# Ignore spaces during merge
 git merge -Xignore-space-change

 # git hard reset particular file
 git checkout HEAD -- my-file.txt

# Sources: 
# https://stackoverflow.com/questions/13716658/how-to-delete-all-commit-history-in-github
# https://stackoverflow.com/questions/348170/how-to-undo-git-add-before-commit
# https://stackoverflow.com/questions/2845731/how-to-uncommit-my-last-commit-in-git
# https://stackoverflow.com/questions/9776527/merging-without-whitespace-conflicts
# https://stackoverflow.com/questions/7147270/hard-reset-of-a-single-file