# UnzipDataflow
git init
git add .
git commit -m “Initial commit”
git remote add origin https://github.com/username/projectname.git
Notes:
I think: git remote add origin git@github.com:username/projectname.git does the same thing.
The ‘origin’ name is arbitrary (As with branch naming, remote alias names are arbitrary – just as ‘master’ has no special meaning but is widely used because git init sets it up by default, ‘origin’ is often used as a remote name because git clone sets it up by default as the cloned-from URL. You can really name it just about anything.)
if step 6 fails, go with step 11 else 12
git remote set-url origin git@github.com:username/projectname.git
git push -u origin master
