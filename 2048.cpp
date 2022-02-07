#include <bits/stdc++.h>
#include <cstdlib>
using namespace std;
int board[4][4];
int dirLine[] = {1, 0, -1, 0};
int dirColumn[] = {0, 1, 0, -1};

pair<int, int> generateunoccuiedposition()
{
     int occupied = 1, line, column;
     while (occupied)
     {
          line = rand() % 4;
          column = rand() % 4;
          if (board[line][column] == 0){
               occupied = 0;
               break;
          }
     }
     return make_pair(line, column);
}

void addpiece()
{
     pair<int, int> position = generateunoccuiedposition();
     board[position.first][position.second] = 2;
}

void newGame()
{
     for (int i = 0; i < 4; i++)
          for (int j = 0; j < 4; j++)
               board[i][j] = 0;

     addpiece();
}
void PrintUI()
{
     system("cls");
     for (int i = 0; i < 4; i++)
     {
          for (int j = 0; j < 4; j++)
          {
               if (board[i][j] == 0)
               {
                    cout <<setw(4)<<"-";
               }
               else
               {
                    cout << setw(4)<<board[i][j] << " ";
               }
          }
          cout << endl;
     }
     cout << "n: new game  , w:up , s:down  , d:right , a:left,q:quit " << endl;
}

bool candomove(int line, int column, int nextline, int nextcolumn)
{
     if (nextline < 0 || nextcolumn < 0 || nextcolumn >= 4 || nextline >= 4 ||
         board[line][column] != board[nextline][nextcolumn] && board[nextline][nextcolumn] != 0)
          return false;
     return true;
}

void applyMove(int direction)
{
     int startLine = 0, startcolumn = 0, linestep = 1, columnstep = 1;
     if (direction == 0)
     {
          startLine = 3;
          linestep = -1;
     }
     if (direction == 1)
     {
          startcolumn = 3;
          columnstep = -1;
     }
     int movepossible = 0,canAddPiece=0;
     do{
          movepossible=0;
     for (int i = startLine; i >= 0 && i < 4; i += linestep)
          for (int j = startcolumn; j >= 0 && j < 4; j += columnstep)
          {

               int nexti = i + dirLine[direction], nextj = j + dirColumn[direction];

            
               if (board[i][j]&&candomove(i, j, nexti, nextj))
               {
                    board[nexti][nextj] += board[i][j];
                    board[i][j] = 0;
                    movepossible = canAddPiece=1;
               }
          }
          PrintUI();
     }while(movepossible);
     if (canAddPiece)
     {
          addpiece();
     }
}
int main()
{
     srand(time(0));
     char Direction[128];
     Direction['s'] = 0;
     Direction['d'] = 1;
     Direction['w'] = 2;
     Direction['a'] = 3;
     newGame();
     while (true)
     {
          PrintUI();
          char cmd;
          cin >> cmd;
          if (cmd == 'n')
          {
               newGame();
          }
          else if (cmd == 'q')
          {
               break;
          }
          else
          {
               int currentDirection = Direction[cmd];
               // cout<<currentDirection<<endl;
               applyMove(currentDirection);
          }
     }
}
