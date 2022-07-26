Made by Riccardo Zamuner & Tommaso Zanette

-human v human chess game
-mvc model

model:

    -base object: generic piece
        -color

    -extension of piece: 
        -king
        -queen
        -knight
        -rook
        -bishop
        -pawn
        -specific image, moves, starting position

    board:
        -2(+1) matrixes: 
            -eventual background (char) (b&w + selected piece)
            -pieces positions (char)
            -available moves (int) (contains 1 if you can move on that cell, otherwise 0)

controller:
    -drag & drop (easier version: click on piece & click on where you want to move)
        -when you click on a piece, get coords by getting mousePosition
        -calculate matrix cell
        -show available moves (hidden matrix with 1 where you can move,)
        -if you click on a cell where you can move, it plays the move, otherwise it resets
    -update view:
        -remove pieces when taken
        -show available moves
    -rules:
        -checks
        -draw rules (repetition, 50 moves)
        -castle
        -win

view:
    -canvas
    -64 squares alternating black & white
    -piece image
    -available moves
