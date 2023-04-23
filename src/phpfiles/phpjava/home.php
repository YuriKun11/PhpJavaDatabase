<?php
session_start();

    if(isset($_SESSION['id']) && isset($_SESSION['username'])){

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="stylemobulok.css">
    <title>Home</title>
</head>
<body>
    <div class="card-wrap">

    <div class="profile">
        <h2>PROFILE</h2>
    </div>

        <div class="card">
            <h1><?php echo $_SESSION['username'];?></h1>
            <h3>Amacc - La Union</h3>
            <h3>BS in <?php echo $_SESSION['course'];?></h3>

            <a class="btn"href="logout.php">Logout</a>

        </div>

    </div>
  
    
    
</body>
</html>

<?php
}else{
    header("Location: index.php");
    exit();

}

?>
