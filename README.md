# SWD4TA020-3008

Windows GitBash

    git init
    git add .
    git config user.email "bcy569@myy.haaga-helia.fi"
    git config user.name "bcy569"
    git commit -m"init"
    git remote add origin git@github.com:bcy569/SWD4TA020-3008.git


    ssh-keygen -t ed25519 -C "bcy569@myy.haaga-helia.fi"


    eval $(ssh-agent -s)
    ssh-add C:/Users/Administrator/.ssh/id_ed25519

Kopioi tiedoston
C:/Users/Administrator/.ssh/id_ed25519.pub
sisältö GitHubiin
https://github.com/settings/keys

    git push -u origin master


git config --global --unset user.name
