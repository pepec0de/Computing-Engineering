function y = recalcularH(x)
%RECALCULARH recalcula H

    if x <= 0.5
        y = 1 - 2*x;
    else
        y = 2*(x - 0.5);
    end
end

